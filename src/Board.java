import java.util.*;

public class Board {
    ArrayList<Note> noteArrayList = new ArrayList<>();
    final List<Note> noteList = Collections.synchronizedList(noteArrayList);
    int width;
    int height;
    ArrayList<String> colours;


    public Board(int width, int height, ArrayList<String> colours) {
        this.width = width;
        this.height = height;
        this.colours = colours;
    }
    /**
     * adds note to noteArrayList through noteList capsule
     * @param new_note - Note object to be added to the arraylist
     */
    public void addNote(Note new_note) {
        boolean add = noteList.add(new_note);
    }


    /**
     * pins all notes that overlap with the x,y coordinate pair
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public void pinNotes(int x, int y) {
        synchronized (noteList) {
            for (Note tempNote : noteList) {
                if (tempNote.withinNote(x, y)) {
                    tempNote.pinNote();
                }
            }
        }
    }

    /**
     * unpins all notes that overlap with the x,y coordinate pair
     * @param x - x coordinate
     * @param y - y coordinate
     */
    public void unpinNotes(int x, int y) {
        synchronized (noteList) {
            ListIterator<Note> i = noteList.listIterator();
            while (i.hasNext()) {
                Note tempNote = i.next();
                if (tempNote.withinNote(x, y)) {
                    tempNote.unpinNote();
                }
            }
        }
    }

    /**
     * Returns an ArrayList object of all of the coordinates of notes as strings in the format "(<x-coordinate>,<y-coordinate>)"
     */
    public ArrayList<String> getNotes() {
        ArrayList<String> coordArray = new ArrayList<>();
        synchronized (noteList) {
            ListIterator<Note> i = noteList.listIterator();
            while (i.hasNext()) {
                Note tempNote = i.next();
                coordArray.add(tempNote.getCoordsAsString());
            }
        }

        return coordArray;
    }

    public ArrayList<String> getNotes(
            String colour,
            int x,
            int y,
            String refersTo) {

        //Instantiates ArrayList for Strings
        ArrayList<String> notesAsStrings;
        //
        ArrayList<Note> noteListSub = matchingColoursList(colour);
        noteListSub = matchingCoords(noteListSub, x, y);
        noteListSub = matchingRefersTo(noteListSub, refersTo);

        notesAsStrings = noteListToStringList(noteListSub);

        return notesAsStrings;
    }

    /**
     * Creates subarray of Notes that have matching colour parameter or
     * new array of Notes matching original array
     * @param colour - input colour parameter
     * @return returns newly created ArrayList
     */
    private ArrayList<Note> matchingColoursList(
            String colour) {
        // Creates empty of note objects
        ArrayList<Note> noteArrayListSub = new ArrayList<>();
        synchronized (noteList) {
            ListIterator<Note> i = noteArrayList.listIterator();
            if (colour.equals("null")) {
                while (i.hasNext()) {
                    Note tempNote = i.next();
                    noteArrayListSub.add(tempNote);
                }
            } else {
                while (i.hasNext()) {
                    Note tempNote = i.next();
                    if (tempNote.colourMatches(colour)) {
                        noteArrayListSub.add(tempNote);
                    }
                }
            }
        }

        return noteArrayListSub;
    }

    /**
     * for empty input, x is given as -1
     * @param notes - ArrayList input from output of matchingColourList
     * @param x - x coordinate for matching
     * @param y - y coordinate for matching
     * @return - returns ArrayList of Notes that satisfy constraint of
     * coordinates within note bounds
     */
    private ArrayList<Note> matchingCoords(
            ArrayList<Note> notes,
            int x,
            int y) {
        // Creates empty of note objects
        ArrayList<Note> noteArrayListSub = new ArrayList<>();
        synchronized (noteList) {
            ListIterator<Note> i = notes.listIterator();
            // if x value is negative it means that the input was
            // not given for coordinates and the output array should
            // match the input array
            if (x < 0) {
                return notes;
            } else { // for positive values of X - appends any notes
                // which satisfy the withinNote() constraint to the
                // output ArrayList
                while (i.hasNext()) {
                    Note tempNote = i.next();
                    if (tempNote.withinNote(x, y)) {
                        noteArrayListSub.add(tempNote);
                    }
                }
            }
        }

        return noteArrayListSub;
    }

    /**
     *
     * @param notes array of notes passed from matchingCoords function
     * @param refersTo input string from GET query
     * @return returns an ArrayList of Notes that satisfy the referenceMatches
     * constraint
     */
    private ArrayList<Note> matchingRefersTo(
            ArrayList<Note> notes,
            String refersTo) {
        ArrayList<Note> noteArrayListSub = new ArrayList<>();
        synchronized (noteList) {
            ListIterator<Note> i = notes.listIterator();
            // if x value is negative it means that the input was
            // not given for coordinates and the output array should
            // match the input array
            if (refersTo.equals("null")) {
                return notes;
            } else {
                while (i.hasNext()) {
                    Note tempNote = i.next();
                    if (tempNote.referenceMatches(refersTo)) {
                        noteArrayListSub.add(tempNote);
                    }
                }
            }
        }

        return noteArrayListSub;
    }

    private ArrayList<String> noteListToStringList(ArrayList<Note> noteListSub) {
        ArrayList<String> notesAsStrings = new ArrayList<>();

        ListIterator<Note> i = noteListSub.listIterator();
        while (i.hasNext()) {
            Note tempNote = i.next();

            String stringNote = noteToString(tempNote);
            notesAsStrings.add(stringNote);
        }
        return notesAsStrings;
    }

    private String noteToString(Note note) {
        // [x,y] coordinates of note
        String stringNote = "( [" + note.getxCoord() + "," + note.getyCoord() + "] ";
        // width of note
        stringNote = stringNote.concat("width=" + note.getWidth() + " ");
        // height of note
        stringNote = stringNote.concat("height=" + note.getHeight() + " ");
        // colour of note
        stringNote = stringNote.concat("colour=" + note.getColour() + "\n");
        // message on note
        stringNote = stringNote.concat("message=" + note.getMessage() + "\n");
        // pinned or unpinned status
        stringNote = note.isPinned()?stringNote =
                stringNote.concat("Pinned) \n"):
                stringNote.concat("Not Pinned) \n");

        return stringNote;
    };
}
