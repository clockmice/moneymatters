import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner scanner = new Scanner(System.in);
        int numberOfFriends = scanner.nextInt();
        int numberOfFriendships = scanner.nextInt();

        Map<Integer, Friend> everybody = new HashMap();

        for (int i = 0; i < numberOfFriends; i++) {
            Friend friend = new Friend(i, scanner.nextInt());
            everybody.put(i, friend);
        }

        for (int i = 0; i < numberOfFriendships; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            everybody.get(first).addFriend(second);
            everybody.get(second).addFriend(first);
        }

        //print out
//        for (int i = 0; i < numberOfFriends; i++) {
//            System.out.println(everybody.get(i).toString());
//        }
        //

        if (isPossible(numberOfFriends, everybody)) {
            System.out.println("POSSIBLE");
        } else {
            System.out.println("IMPOSSIBLE");
        }

    }

    public static boolean isPossible(int numberOfFriends, Map<Integer, Friend> everybody){
        List<List<Friend>> groups = new ArrayList<>();

        for (int i = 0; i < numberOfFriends; i++) {
            if (everybody.get(i).isInGroup()) {
                continue;
            }
            List<Friend> group = new ArrayList<>();
            groups.add(group);
            divideInGroups(i, everybody, group);
        }

        //print groups
//        StringBuilder sb = new StringBuilder();
//        for (int j = 0; j < groups.size(); j++) {
//
//            sb.append("Group: ");
//            for (int k = 0; k < groups.get(j).size(); k++) {
//                sb.append(groups.get(j).get(k).getId() + ", ");
//            }
//        }
//        System.out.println(sb.toString());
        //

        for (int l = 0; l < groups.size(); l++) {
            int sum = 0;
            for (Friend friend : groups.get(l)) {
                sum = sum + friend.getOwes();
            }
            if (sum != 0) {
                return false;
            }
        }

        return true;
    }

    public static void divideInGroups(int friendID, Map<Integer, Friend> friends, List<Friend> group) {
        Friend friend = friends.get(friendID);
        if (friend.isInGroup()) {
            return;
        }
        group.add(friend);
        friend.setInGroup(true);
        for (int i = 0; i < friend.getFriends().size(); i++) {
            divideInGroups(friend.getFriends().get(i), friends, group);
        }
    }

}

class Friend {
    private int id;
    private int owes;
    private boolean inGroup;
    private List<Integer> friends;

    public Friend(int id, int owes) {
        this.id = id;
        this.owes = owes;
        this.inGroup = false;
        this.friends = new ArrayList<>();
    }

    public void addFriend(int friend) {
        friends.add(friend);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwes() {
        return owes;
    }

    public void setOwes(int owes) {
        this.owes = owes;
    }

    public boolean isInGroup() {
        return inGroup;
    }

    public void setInGroup(boolean inGroup) {
        this.inGroup = inGroup;
    }

    public List<Integer> getFriends() {
        return friends;
    }

    public void setFriends(List<Integer> friendsOf) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Friend: " +
                "id = " + id +
                ", owes = " + owes +
                ", in group = " + inGroup +
                ", friends = " + friends;
    }
}
