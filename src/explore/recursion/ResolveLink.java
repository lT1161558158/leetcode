package explore.recursion;

import java.util.Stack;

/**
 * 反转一个单链表。
 */
public class ResolveLink {

    public ListNode reverseList(ListNode head) {
        return loopReverseList(head);
    }

    /**
     * 使用栈模拟递归
     */
    ListNode loopReverseList(ListNode head) {
        if (head == null)
            return null;
        Stack<ListNode> stack = new Stack<>();
        for (ListNode node = head; node != null; node = node.next) {
            stack.push(node);
        }
        ListNode result = stack.pop();
        ListNode cur = result;
        while (!stack.empty()) {
            ListNode pop = stack.pop();
            cur.next = pop;
            cur = pop;
        }
        cur.next = null;
        return result;
    }

    /**
     * 纯循环完成
     */
    ListNode loopReverseListReal(ListNode head) {
        ListNode before = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = before;
            before = cur;
            cur = temp;
        }
        return before;
    }

    /**
     *
     */
    ListNode recursionReverseList(ListNode node) {
        if (node == null)
            return null;
        if (node.next == null)
            return node;
        ListNode last = node.next;//记录反转后的链表尾
        ListNode recursion = recursionReverseList(node.next);//反转链表.获得反转后的表头
        node.next = null;//断开原有的连接
        last.next = node;//将当前节点追加到新链表尾
        return recursion;
    }

}
