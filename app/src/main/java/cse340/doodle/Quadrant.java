package cse340.doodle;

public enum Quadrant {
    TOPLEFT,        // Top left to bottom right
    TOPRIGHT,       // Top right to bottom left
    BOTTOMRIGHT,    // Bottom right to top left
    BOTTOMLEFT,     // Bottom left to top right
    HORIZONTALLEFTRIGHT, // HORIZONTAL line from left to right
    HORIZONTALRIGHTLEFT, // HORIZONTAL line from right to left
    VERTICALTOPBOTTOM,   // VERTICAL line from top to bottom
    VERTICALBOTTOMTOP;   // VERTICAL line from bottom to top

    @Override
    public String toString() {
        switch (this) {
            case TOPLEFT:
                return "line from top left to bottom right";
            case TOPRIGHT:
                return "line from Top right to bottom left";
            case BOTTOMRIGHT:
                return "line from bottom right to top left";
            case BOTTOMLEFT:
                return "line from Bottom left to top right";
            case HORIZONTALLEFTRIGHT:
                return "horizontal line from left to right";
            case HORIZONTALRIGHTLEFT:
                return "horizontal line from right to left";
            case VERTICALTOPBOTTOM:
                return "vertical line from top to bottom";
            case VERTICALBOTTOMTOP:
                return "vertical line from bottom to top";
            default:
                return "line";
        }
    }
}
