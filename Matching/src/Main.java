//Author: Amy Nguyen '22

public class Main {
    public static void main(String[] args) throws java.io.IOException{

///////////////////////////////////////////// read in data from text files /////////////////////////////////////////////

        String filePreFresh;
        String[] preFreshStart;

        filePreFresh = new String(java.nio.file.Files.readAllBytes
                (java.nio.file.Paths.get("preFresh.txt")));
        preFreshStart = filePreFresh.split("\\r?\\n");
        Person[] preFresh = makePersonArray(preFreshStart);

        String fileHost;
        String[] hostStart;


        fileHost = new String(java.nio.file.Files.readAllBytes
                (java.nio.file.Paths.get("host.txt")));
        hostStart = fileHost.split("\\r?\\n");
        Person[] host = makePersonArray(hostStart);

///////////////////////////////////////// utilize Hash Table Data Structure #1 /////////////////////////////////////////

        System.out.println("Utilizing a Hash Table Data Structure!");
        System.out.println();
        System.out.println("OUTPUT...");

        HashTable preFreshHT = new HashTable(10); //based on majors
        HashTable hostHT = new HashTable(10);

        //Call HT add() on each element of preFresh

        for (Person fresh : preFresh) {
            preFreshHT.add(fresh);
        }

        //Call HT add() on each element of host
        for (Person person : host) {
            hostHT.add(person);
        }


        List noMatchHTPF = new List();
        List noMatchHTH = new List();

        //Remove unmatchable pairs
        for (int i = 0; i< preFreshHT.size; i++) {
            while(preFreshHT.table[i].count != hostHT.table[i].count){
                if(preFreshHT.table[i].count < hostHT.table[i].count) {
                    noMatchHTH.add(hostHT.table[i].delete());
                } else {
                    noMatchHTPF.add(preFreshHT.table[i].delete());
                }
            }

        }

        //Call hashTable() on preFreshHT and hostHT to match by majors
        List pairedUpHT = hashTable(preFreshHT, hostHT);

        //Call printPairs() on pairedUpHT
        printPairs(pairedUpHT);
        printNonPairs(noMatchHTPF, noMatchHTH);


//////////////////////////////////// utilize Adjacency List Graph Data Structure #2 ////////////////////////////////////

        System.out.println("Utilizing an Adjacency List Graph Data Structure!");
        System.out.println();
        System.out.println("OUTPUT...");

        AdjacencyList preFreshAJ = addToAdjacencyList(preFresh);
        AdjacencyList hostAJ = addToAdjacencyList(host);

        List noMatchAJPF = new List();
        List noMatchAJH = new List();

        //Remove unmatchable pairs
        for (int i = 0; i< preFreshHT.size; i++) {
            while(preFreshAJ.adjacent[i].count != hostAJ.adjacent[i].count){
                if(preFreshAJ.adjacent[i].count < hostAJ.adjacent[i].count) {
                    noMatchAJH.add(hostAJ.adjacent[i].delete());
                } else {
                    noMatchAJPF.add(preFreshAJ.adjacent[i].delete());
                }
            }

        }

        //Call adjacencyList() on preFreshAJ and hostAJ to match by majors
        List pairedUpAJ = adjacencyList(preFreshAJ, hostAJ);

        //Call printPairs() on pairedUpAJ
        printPairs(pairedUpAJ);
        printNonPairs(noMatchAJPF, noMatchAJH);


    }



////////////////////////////////////////// Create an array of 'Person' Object  //////////////////////////////////////////

    private static Person[] makePersonArray(String[] temp){

        Person[] returnThis = new Person[temp.length/3];
        int j = 0;
        while(j < returnThis.length){
            for(int i = 0; i< temp.length-2; i = i+3){
                Person toAdd = new Person(temp[i],temp[i+1], temp[i+2]);
                returnThis[j] = toAdd;
                j++;
            }
        }

        return returnThis;

    }

////////////////////////////////////////////////// Print Matched Pairs //////////////////////////////////////////////////

    public static void printPairs(List pairs){
        while(pairs.count != 0 ){
            Person first = pairs.delete(); //technically the last person
            Person second = pairs.delete(); //technically the second to last person
            System.out.println("prospective student " + second.getName() + " is paired with amherst student " +
                    first.getName() + " in room " + first.getRoom() + "." + " they are both " + first.getMajor() + " majors!");
        }
        System.out.println();
    }
//////////////////////////////////////////////// Print Non Matched Pairs ////////////////////////////////////////////////

    public static void printNonPairs(List pF, List h){

        //Print students who still need a host
        if(pF.count != 0 && h.count != 0){
            System.out.print("the following prospective students could not be matched: ");
            pF.print();

            //Print available hosts
            System.out.print("the following hosts have not been matched: ");
            h.print();
        }

        System.out.println();

    }
////////////////////////////////////////// Match Pairs Stored in Hash Tables  //////////////////////////////////////////

    public static List hashTable(HashTable pF, HashTable h){

        String[] majors = {"History", "Math/Statistics", "Religion", "Biology", "Philosophy",
                             "Chemistry", "Computer Sciences", "Economics", "English", "Political Sciences"};

        List paired = new List();

        for (String major : majors) {
            int location = hash(major);
            while(pF.table[location].count != 0 ){
                paired.add(pF.table[location].delete());
                paired.add(h.table[location].delete());
            }

            }

        return paired;
    }

///////////////////////////////////////////// Hash Function for Hash Table  /////////////////////////////////////////////

    public static int hash(String s) {

        switch (s) {

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

////////////////////////////////////// Match Pairs Stored in Adjacency List Graph  //////////////////////////////////////

    public static List adjacencyList(AdjacencyList pF, AdjacencyList h){

        List paired = new List();

        for (int i = 0; i < pF.vertices; i++) {
            while(pF.adjacent[i].count != 0 ){
                paired.add(pF.adjacent[i].delete());
                paired.add(h.adjacent[i].delete());
            }

        }

        return paired;
    }


//////////////////////// Add to Adjacency List where Indexes Map to Persons with the Same Major ////////////////////////

    private static AdjacencyList addToAdjacencyList(Person[] temp){
        AdjacencyList tempGraph = new AdjacencyList(10);

        for (Person person : temp) {
            switch (person.getMajor()) {
                case "History":
                    tempGraph.addEdge(0, person);
                    break;
                case "Math/Statistics":
                    tempGraph.addEdge(1, person);
                    break;
                case "Religion":
                    tempGraph.addEdge(2, person);
                    break;
                case "Biology":
                    tempGraph.addEdge(3, person);
                    break;
                case "Philosophy":
                    tempGraph.addEdge(4, person);
                    break;
                case "Chemistry":
                    tempGraph.addEdge(5, person);
                    break;
                case "Computer Sciences":
                    tempGraph.addEdge(6, person);
                    break;
                case "Economics":
                    tempGraph.addEdge(7, person);
                    break;
                case "English":
                    tempGraph.addEdge(8, person);
                    break;
                case "Political Sciences":
                    tempGraph.addEdge(9, person);
                    break;
            }
        }

        return tempGraph;
    }

}

/*  OTHER METHODS
   private static void findElement(Person[] toFind, BST tree) {
        for(Person value : toFind){
            boolean found = tree.find(tree.root, value.getMajor());
            String f1 = " ";
            if(found){
                f1 = "found it :D";
            } else {
                f1 = "not found :(";
            }
            System.out.println("Tried to find " + value + ": " + f1);
        }
    }

    public static Person[] sortPeople(Person[] unsorted){ //sort from male -> female -> no preference
        Person[] sorted;

        for (int i=1 ;i<unsorted.length; i++)
        {
            Person temp = unsorted[i];

            int j = i - 1;
            while (j >= 0 && temp.getMajor().length() < unsorted[j].getMajor().length())
            {
                unsorted[j+1] = unsorted[j];
                j--;
            }
            unsorted[j+1] = temp;
        }

        sorted = unsorted;
        return sorted;
    }

    ///////////////////////////////////////// utilize Queue/Stack Data Structure #3 /////////////////////////////////////////
        System.out.println("Utilizing a Queue/Stack Graph Data Structure!");
        System.out.println();
        System.out.println("OUTPUT...");

        Stack preFreshS = new Stack();
        Stack hostS = new Stack();

        for (Person pF : preFresh) {
            preFreshS.push(pF);
        }

        for (Person h : host) {
            hostS.push(h);
        }

        List pairedUpS = stack(preFreshS, hostS);
        pairedUpS.print();

        ///////////////////////////////////////////// Match Pairs Stored in Stack  /////////////////////////////////////////////

    public static List stack(Stack pF, Stack h){

        List temp = new List();
        while(pF.size != 0){
            Person tempPF = pF.pop();
              while(h.size != 0){
                  Person tempH = h.pop();
                  if(tempPF.getMajor().equals(tempH.getMajor())){
                      temp.add(tempPF);
                      temp.add(tempH);
                      break;
                  } else {
                      h.push(tempH);
                  }
              }


        }

        return temp;
    }
*/



