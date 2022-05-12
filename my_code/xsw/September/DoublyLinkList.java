package xsw.September;

/**
 * 自定义一个双向链表
 *
 * @author Administrator
 */
public class DoublyLinkList {

    static class Link {
        public int data;    //数据域
        public Link next;    //指向下一个结点
        public Link previous;

        public Link(int data) {
            super();
            this.data = data;
        }

        //打印结点的数据域
        public void displayLink() {
            System.out.print("{" + data + "} ");
        }
    }

    private Link first;
    private Link last;

    public DoublyLinkList() {
        first = null;
        last = first;
    }

    //头部插入
    public void insertFirst(int data) {
        Link newNode = new Link(data);
        if (isEmpty()) {
            last = newNode;
        } else {//如果不是第一个结点的情况
            first.previous = newNode;    //将还没插入新结点之前链表的第一个结点的previous指向newNode
        }
        newNode.next = first;        //将新结点的next指向first
        first = newNode;            //将新结点赋给first（链接）成为第一个结点
    }

    //尾部插入
    public void insertLast(int data) {
        Link newNode = new Link(data);
        if (isEmpty()) {
            first = newNode;        //若链表为空，则将first指向新的结点（newNode）
        } else {
            newNode.previous = last;//将last的previous指向last（last永远指向的是最后一个结点）【此时还没有插入新的结点newNode，所以last指向的是当前链表的最后一个结点】
            last.next = newNode;    //将last.next(当前链表最后一个结点的next域)指向新的结点newNode
        }
        last = newNode;                //由于插入了一个新的结点，又因为是尾部插入，所以将last指向newNode
    }

    //某个结点的后部插入
    public void insertAfter(int key, int data) {
        Link newNode = new Link(data);
        Link current = first;
        while ((current != null) && (current.data != key)) {
            current = current.next;
        }
        //若当前结点current为空
        if (current == null) {                    //current为null有两种情况 一种是链表为空，一种是找不到key值
            if (isEmpty()) {                    //1、链表为空
                first = newNode;                //则插入第一个结点（其实可以调用其它的Insert方法）
                last = newNode;                    //first和last均指向该结点（第一个结点）
            } else {
                last.next = newNode;        //2、找不到key值
                newNode.previous = last;    //则在链表尾部插入一个新的结点
                last = newNode;
            }
        } else {
            if (current == last) {                    //第三种情况，找到了key值，分两种情况                                               两
                newNode.next = null;            //1、key值与最后结点的data相等                                                                  条
                last = newNode;                    //由于newNode将是最后一个结点，则将last指向newNode    线
            } else {
                newNode.next = current.next;        //2、两结点中间插入                                                                                                                            四
                current.next.previous = newNode;    //将current当前结点的下一个结点赋给newNode.next                条
            }                                        //将current下一个结点即current.next的previous域指向current    线
            current.next = newNode;                    //将当前结点的next域指向newNode
            newNode.previous = current;                //将新结点的previous域指向current（current在newNode前面一个位置）

        }

    }

    //检查链表是否为空
    public boolean isEmpty() {
        return (first == null);

    }

    //从头部删除结点
    public Link deleteFirst() {
        Link temp = first;
        if (first.next == null) {            //若链表只有一个结点，删除后链表为空，将last指向null
            last = null;
        } else {
            first.next.previous = null;        //若链表有两个（包括两个）以上的结点 ，因为是头部插入，则first.next将变成第一个结点，其previous将变成null
        }
        first = first.next;                //将first.next赋给first
        return temp;                    //返回删除的结点
    }

    //从尾部删除结点
    public Link deleteLast() {
        Link temp = last;
        if (first.next == null) {        //如果链表只有一个结点，则删除以后为空表,last指向null
            first = null;
        } else {
            last.previous.next = null;    //将上一个结点的next域指向null
        }
        last = last.previous;            //上一个结点称为最后一个结点，last指向它
        return temp;                    //返回删除的结点
    }

    //按值删除
    public Link deleteKey(int key) {
        Link current = first;
        while (current != null && current.data != key) {        //遍历链表寻找该值所在的结点
            current = current.next;
        }
        if (current == null) {                        //若当前结点指向null则返回null，
            return null;                        //两种情况当前结点指向null，一是该链表为空链表，而是找不到该值
        } else {
            if (current == first) {                //如果current是第一个结点
                first = current.next;            //则将first指向它，将该结点的previous指向null,其余不变
                current.next.previous = null;
            } else if (current == last) {            //如果current是最后一个结点
                last = current.previous;        //将last指向当前结点的上一个结点（我们将当前结点除名了以后它便不再是最后一个了）
                current.previous.next = null;    //相应的要删除结点的上一个结点的next域应指向null
            } else {
                current.previous.next = current.next;        //当前结点的上一个结点的next域应指向当前的下一个结点
                current.next.previous = current.previous;    //当前结点的下一个结点的previous域应指向当前结点的上一个结点
            }
        }
        return current;        //返回
    }


    //从头部开始演绎
    public void displayForward() {
        System.out.print("List(first--->last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }

    //从尾部开始演绎
    public void displayBackward() {
        System.out.print("List(last--->first): ");
        Link current = last;
        while (current != null) {
            current.displayLink();
            current = current.previous;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkList theList = new DoublyLinkList();
        theList.insertFirst(20);
        theList.insertFirst(40);
        theList.insertFirst(60);
        theList.insertLast(10);
        theList.insertLast(30);
        theList.insertLast(50);

        theList.displayForward();
        theList.displayBackward();

        theList.deleteFirst();
        theList.deleteLast();
        theList.deleteKey(10);

        theList.displayForward();

        theList.insertAfter(20, 70);
        theList.insertAfter(30, 80);

        theList.displayForward();
        theList.displayBackward();
    }

}