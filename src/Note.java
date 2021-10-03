public class Note {
    private int xcoord;
    private int ycoord;
    private int width;
    private int height;
    private String colour;
    private String message;
    private Boolean status;

    public Note(int xcoord, int ycoord, int width, int height,
            String colour, String message, Boolean status) {
            this.xcoord = xcoord;
            this.ycoord = ycoord;
            this.width = width;
            this.height = height;
            this.colour = colour;
            this.message = message;
            this.status = status;
    }

    // Getter Methods
    public int getxCoord() {
        return this.xcoord;
    }
    public int getyCoord() {
        return this.xcoord;
    }
    public int getWidth() {
        return this.xcoord;
    }
    public int getHeight() {
        return this.xcoord;
    }
    public String getColour() {
        return this.colour;
    }
    public String getMessage() {
        return this.message;
    }
    public Boolean isPinned() {
        return this.status;
    }

    // Setter Methods
    public void pinNote() {
        this.status = true;
    }
    public void unpinNote() {
        this.status = false;
    }

    //Functional Methods

}
