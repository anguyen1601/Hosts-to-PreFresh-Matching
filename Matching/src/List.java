
public class List {
    private Node first;
    private Node last;
    int count;

    public List() {
        first = new Node(); //first array element is just an empty pointer
        last = first;
    }

    public int size() {

        return count;
    }

    public void print(){
        if (count > 0) {
            Node temp = first;
            for (int i = 0; i< count; i++) {
                temp = temp.next;
                System.out.println(temp.value.getName());
            }
        }
    }

    public void add(Person toAdd) { //add to end
        Node addNode = new Node(toAdd);
        addNode.previous = last;
        last.next = addNode;
        last = addNode;
        count++;

    }
    public boolean contains(Person toFind){
        Node nodeFind = first.next;

        for (int i = 0; i < count; i++) {
            if (nodeFind.value == toFind) {
                return true;
            } else {
                nodeFind = nodeFind.next;
            }
        }
        return false;
    }

    public Node find(Person toFind) {

        Node nodeFind = first.next;

        for (int i = 0; i < count; i++) {
            if (nodeFind.value.equals(toFind)) {
                return nodeFind;
            } else {
                nodeFind = nodeFind.next;
            }
        }
        return null;
    }

    public Person delete(){
        if(first.next == null){
            return null;
        }
        Node nodeDelete = last;
        Node newLast = last.previous;
        newLast.next = null;
        last = newLast;
        count--;
        return nodeDelete.value;

    }

    public void remove(Person toRemove) {

        Node nodeRemove = find(toRemove);
        if (nodeRemove != null) {
            if (nodeRemove == last) {
                nodeRemove.previous.next = null;
                last = nodeRemove.previous;
            } else {
                nodeRemove.previous.next = nodeRemove.next;
                nodeRemove.next.previous = nodeRemove.previous;
            }
            count--;
        }

    }

}

