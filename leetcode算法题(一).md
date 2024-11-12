# leetcode

## 两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。你可以按任意顺序返回答案。  
示例 1：  
输入：nums = [2,7,11,15], target = 9,输出：[0,1],解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。  
示例 2：输入：nums = [3,2,4], target = 6,输出：[1,2]  
示例 3：输入：nums = [3,3], target = 6,输出：[0,1]  

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] results = new int[2];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    results[0] = i;
                    results[1] = j;
                    break;
                }
            }
        }
        return results;
    }
}
```

## 两数相加
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
输入：l1 = [2,4,3], l2 = [5,6,4],输出：[7,0,8] ,解释：342 + 465 = 807.  
输入：l1 = [0], l2 = [0],输出：[0]  
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9],输出：[8,9,9,9,0,0,0,1]  

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 将l1和l2反转得到int类型的值并求和
        int value = getValFromList(reverse(l1)) + getValFromList(reverse(l2));
        // 将求和的值再转成链表
        ListNode ls = reverse(fromIntToList(value));
        return ls;
    }

    // 将链表反转
   public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
    }
        return prev;
}

    // 从链表获取值:输入[2,4,3]输出243
    public int getValFromList(ListNode listNode){
        String result = ""+listNode.val;
        while(listNode.next!=null){
            listNode = listNode.next; 
            result = result+listNode.val;
        }
        return Integer.parseInt(result);
    }

    // 将数字转成链表:输入243输出[2,4,3]
    public ListNode fromIntToList(int s) {
        String value = String.valueOf(s);
        char[] charArray = value.toCharArray();
        ListNode dummyHead = new ListNode(0);  // 使用虚拟头节点简化操作
        ListNode current = dummyHead;
    for (int i = 0; i < charArray.length; i++) {
        current.next = new ListNode(Character.getNumericValue(charArray[i]));
        current = current.next;
    }
        return dummyHead.next;  // 返回真实头节点
}
}
```
标准解答：java递归
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return this.addTwoNumbers2(l1,l2,0);
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2,int a) {
        if(l1==null&&l2==null){return a==0?null:new ListNode(a);}
        if(l1 !=null){a += l1.val;l1 = l1.next;}
        if(l2 !=null){a += l2.val;l2 = l2.next;}
        return new ListNode(a%10,addTwoNumbers2(l1,l2,a/10));
    }
}
```

## 无重复字符的最长子串
给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。  
示例 1:输入: s = "abcabcbb",输出: 3 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。  
示例 2:输入: s = "bbbbb",输出: 1,解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。  
示例 3:输入: s = "pwwkew",输出: 3,解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。  
常规思路
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        if ("".equals(s.trim())) {
            return 1;
        }
        int len = 1;
        String temp;
        int begin = 0;
        int end;
        for (; begin < s.length() - 1; begin++){
            for (end = begin + 1; end < s.length() + 1; end++) {
                temp = s.substring(begin, end);
                if (containRepeatChar(temp)) {
                    break;
                } else {
                    if (len < temp.length()) {
                        len = temp.length();
                    }
                }
            }
        }
        return len;
    }

    //判断某个字符串中是否有重复字符
    public boolean containRepeatChar(String str){
        if(str==null||str.isEmpty()){
            return false;
        }
        char[] elements=str.toCharArray();
        for(char e:elements){
            if(str.indexOf(e)!=str.lastIndexOf(e)){
                return true;
            }
        }
        return false;
    }
}
```
窗口滑动
```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 用于记录字符上一次出现的位置
        int[] lastSeen = new int[128];
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1; // 初始化为 -1，表示字符还没有出现过
        }
        int n = s.length();
        int maxLength = 0; // 记录最长无重复字符子串的长度
        int start = 0; // 滑动窗口的起始位置
        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i); // 获取当前字符
            int index = (int) currentChar; // 将字符转换为 ASCII 值
            // 如果当前字符在滑动窗口内已经出现过
            if (lastSeen[index] >= start) {
                // 更新滑动窗口的起始位置
                start = lastSeen[index] + 1;
            }
            // 更新最长无重复字符子串的长度
            maxLength = Math.max(maxLength, i - start + 1);
            // 更新当前字符的最新出现位置
            lastSeen[index] = i;
        }
        return maxLength;
    }
}
```
## 最长回文子串

暴力解法
```java
class Solution {
    public String longestPalindrome(String s) {
        int len = 0;
        String result="";
        for(int i=0;i<s.length()-1;i++){
            for(int j =i+1;j<s.length();j++){
                if(ishw(s.substring(i,j+1)) && s.substring(i,j+1).length()>len){
                    len=s.substring(i,j+1).length();
                    result = s.substring(i,j+1);
                }
            }
        }
        return result;

    }

    public boolean ishw(String s){
        boolean flag = true;
        int len = s.length();
        // 偶数
        if(len%2 == 0){
            for(int i=0;i<len/2;i++){
                if(s.charAt(i) != s.charAt(len-1-i)){
                    flag =  false;
                    break;
                }
            }
        }else{
            // 奇数
            for(int i=0;i<(len-1)/2;i++){
                if(s.charAt(i) != s.charAt(len-1-i)){
                    flag = false;
                    break;
                }
        }
        return flag;
    }
}
```

## Z字形变换

将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
P   A   H   N  
A P L S I I G  
Y   I   R  
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。请你实现这个将字符串进行指定行数变换的函数：string convert(string s, int numRows);  
示例 1：输入：s = "PAYPALISHIRING", numRows = 3，输出："PAHNAPLSIIGYIR"  
示例 2：输入：s = "PAYPALISHIRING", numRows = 4，输出："PINALSIGYAHRPI" 

```java
public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s; // 如果只有一行，直接返回原字符串

        // 创建一个列表数组，每个列表代表一行
        List<List<Character>> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new ArrayList<>());

        int curRow = 0;
        boolean goingDown = false;

        // 遍历字符串，分配每个字符到对应的行
        for (char c : s.toCharArray()) {
            rows.get(curRow).add(c);
            // 判断是否转向
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            // 更新当前行
            curRow += goingDown ? 1 : -1;
        }

        // 将所有行连接成一个字符串
        StringBuilder ret = new StringBuilder();
        for (List<Character> row : rows) {
            for (Character c : row)
                ret.append(c);
        }
        return ret.toString();
    
}
```

## 整数反转

```java
class Solution {
    public int reverse(int x) {
        String s = String.valueOf(x);
        char flag='+';
        String result="";
        if(s.charAt(0)=='-'){
            flag='-';
            s = s.substring(1);
        }
        s = s.replaceAll("0+$", "");
        int len = s.length();
        char[] chars = new char[len];
    
        for(int i=0;i<len;i++){
            result = result + s.charAt(len-i-1);
        }
        if(flag == '-'){
            result = flag+result;
        }
        return Integer.parseInt(result);
    }

}
```











