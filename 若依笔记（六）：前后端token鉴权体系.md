# 若依笔记（六）：前后端token鉴权体系
简单总结下若依的前后端token鉴权体系流程：
1、前端是通过/login接口来获取jwt-token的，jwt的配置在后端的application.yml中    
2、后端处理/login请求时先检验redis中验证码然后使用spring-security内部机制（过滤链），调用DaoAuthenticationProvider的retrieveUser->loadUserByUsername()来校验用户密码；
3、/login接口使用spring-security内部机制会生成authentication这一核心对象，authentication->Principal属性是用户信息LoginUser（userDetail的子类）  
4、前端通过/login获取的token会保存到Cookies的Admin-Token中，在每次请求时从Cookies中获取dmin-Token的值并放header中以Authentication的value携带给后端；  
5、后端使用SecurityConfig中指定的UsernamePasswordAuthenticationFilter从除/login外请求的header中Authentication这个key中获取token校验权限；  
6、前端是静态路由+动态路由方式渲染菜单，路由拦截会首先/getInfo接口获取菜单列表；  
### 前端/login
前端登录代码的登录事件调用hanleLogin，触发store中dispatch即Login这一action动作（封装在src/store/modules/user.js中），然后Login动作中会有setToken方法把返回的token放在本地的cookies中，并且requets.js的全局拦截会首先看有没有token并携带。
```js
// login.vue中登录实际调用方法
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" }).catch(() => {});
            })
            .catch(() => {
              this.loading = false;
              if (this.captchaEnabled) {
                this.getCode();
              }
            });
        }
      });
    }
```
Login是一个action，定义在src/store/modules/user.js中  
```js
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {
          setToken(res.token)
          commit('SET_TOKEN', res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
```
Login中的setToken将token放在Admin-Token的value中   
```js
const TokenKey = 'Admin-Token'
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}
// Login中getToken在request.js全局配置中使用
export function getToken() {
  return Cookies.get(TokenKey)
}
// 在src/utils/requests.js中有全局的请求拦截，将Bearer+getToken结果放在Authorization中（header）
service.interceptors.request.use(config => {
  // 是否需要设置 token
  const isToken = (config.headers || {}).isToken === false
  // 是否需要防止数据重复提交
  const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
  if (getToken() && !isToken) {
    config.headers['Authorization'] = 'Bearer ' + getToken() // 让每个请求携带自定义token 请根据实际情况自行修改
  }
```
### 后端生成token
后端/login方法会被SecurityConfig定义的过滤链放行，并使用内部机制调用DaoAuthenticationProvider来鉴权，生成
```java
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
```
SysLoginService->login方法的核心三行代码如下，authenticate方法会调用DaoAuthenticationProvider->authenticate->loadUserByUsername方法鉴权
```java
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);

// 为什么会使用DaoAuthenticationProvider的authenticate方法来鉴权，因为在加载使用判断userDetailsService是否存在；
class InitializeUserDetailsBeanManagerConfigurer extends GlobalAuthenticationConfigurerAdapter {

	static final int DEFAULT_ORDER = Ordered.LOWEST_PRECEDENCE - 5000;

	private final ApplicationContext context;

	/**
	 * @param context
	 */
	InitializeUserDetailsBeanManagerConfigurer(ApplicationContext context) {
		this.context = context;
	}

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.apply(new InitializeUserDetailsManagerConfigurer());
	}

	class InitializeUserDetailsManagerConfigurer extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			if (auth.isConfigured()) {
				return;
			}
			UserDetailsService userDetailsService = getBeanOrNull(UserDetailsService.class);
			if (userDetailsService == null) {
				return;
			}
			PasswordEncoder passwordEncoder = getBeanOrNull(PasswordEncoder.class);
			UserDetailsPasswordService passwordManager = getBeanOrNull(UserDetailsPasswordService.class);
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setUserDetailsService(userDetailsService);
			if (passwordEncoder != null) {
				provider.setPasswordEncoder(passwordEncoder);
			}
			if (passwordManager != null) {
				provider.setUserDetailsPasswordService(passwordManager);
			}
			provider.afterPropertiesSet();
			auth.authenticationProvider(provider);
		}
```
security内部将user信息定义成principal，密码信息定义成credentials
```java
	public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}
```
loadUserByUsername 是UserDetailsService接口提供的：
```java
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysUser user = userService.selectUserByUserName(username);
        if (StringUtils.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        else if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        }
        else if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }
        passwordService.validate(user);
        return createLoginUser(user);
    }
```
loadUserByUsername中调用的selectUserByuserName方法会查询selectUserVo这个mapper方法，查表时候其实已经校验了权限、用户状态等信息  
```sql
          select u.user_id, u.dept_id, u.user_name, u.nick_name, u.email, u.avatar, u.phonenumber, u.password, u.sex, u.status, u.del_flag, u.login_ip, u.login_date, u.create_by, u.create_time, u.remark, 
          d.dept_id, d.parent_id, d.ancestors, d.dept_name, d.order_num, d.leader, d.status as dept_status,
          r.role_id, r.role_name, r.role_key, r.role_sort, r.data_scope, r.status as role_status
          from sys_user u
  		    left join sys_dept d on u.dept_id = d.dept_id
  		    left join sys_user_role ur on u.user_id = ur.user_id
  		    left join sys_role r on r.role_id = ur.role_id
```
token还会被后端放在redis中，后续拿到token后在redis中校验；
### 后端拦截
SecurityConfig使用security内部过滤链
```java
// 添加JWT filter
httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
// 通过Authorization获取到user，刷新redis缓存，并在SecurityContextHolder上下文中塞进去了token信息
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNull(SecurityUtils.getAuthentication()))
        {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
// 通过getToken获取到并且从redis中获取用户信息
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            try
            {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                String userKey = getTokenKey(uuid);
                LoginUser user = redisCache.getCacheObject(userKey);
                return user;
            }
            catch (Exception e)
            {
                log.error("获取用户信息异常'{}'", e.getMessage());
            }
        }
        return null;
    }
// getToken
    @Value("${token.header}")
    private String header;
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }
// token.header就在application.yml中
token:
  # 令牌自定义标识
  header: Authorization
```
### 前端的动态路由
动态的路由判断之前有详细描述；

