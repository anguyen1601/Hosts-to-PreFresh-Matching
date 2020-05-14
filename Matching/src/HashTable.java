public class HashTable {
    int size;
     List[] table;

    public HashTable(int tableSize) {

        size = tableSize;
        table = new List[size];
        for (int i = 0; i < size; i++)
            table[i] = new List();
    }

    private int hash(String s) {
        switch (s) {  //must add breaks

            case "History":
                return 0;
            case "Math/Statistics":
                return 1;
            case "Religion":
                return 2;
            case "Biology":
                return 3;
            case "Philosophy":
                return 4;
            case "Chemistry":
                return 5;
            case "Computer Sciences":
                return 6;
            case "Economics":
                return 7;
            case "English":
                return 8;
            case "Political Sciences":
                return 9;

        }
        return -1;
    }

    private int hash2(String s) { //add each char value to a changing value, then raise to a power, mod by length
        int hash = 0;
        int exponent = s.length() + 1;

        for (int i = 0; i < s.length(); i++) {
            hash = ((hash * 41 + s.charAt(i)) ^ exponent) % table.length;
            exponent--;
        }
        return hash;
    }

    public void add(Person temp) {

        String s = temp.getMajor();
        int index = hash(s);
        if(find(temp)){
            return;
        }
        List toAdd = table[index];
        toAdd.add(temp);
        table[index] = toAdd;
    }


    public boolean find(Person temp) {
        String s = temp.getMajor();
        int index = hash(s);
        Node found = table[index].find(temp);

        if (found == null) {
            return false;
        }

        return found.value == temp;
    }

    public void remove(Person temp) {
        String s = temp.getMajor();
        int index = hash(s);

        if (find(temp)) {
            table[index].remove(temp);
        }

    }

    public Person findMajor(String major) {
        int index = hash(major);

     return null;
    }

    public void print() {
        for (List list : table) {
            list.print();
        }
    }
}