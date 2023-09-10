package model.enumerations;

public enum IconEnum {
    WBISHOP_ICON(".\\src\\images\\whiteBishop.png"),
    WKING_ICON(".\\src\\images\\whiteKing.png"),
    WKNIGHT_ICON(".\\src\\images\\whiteKnight.png"),
    WPAWN_ICON(".\\src\\images\\whitePawn.png"),
    WQUEEN_ICON(".\\src\\images\\whiteQueen.png"),
    WROOK_ICON(".\\src\\images\\whiteRook.png"),
    BBISHOP_ICON(".\\src\\images\\blackBishop.png"),
    BKING_ICON(".\\src\\images\\blackKing.png"),
    BKNIGHT_ICON(".\\src\\images\\blackKnight.png"),
    BPAWN_ICON(".\\src\\images\\blackPawn.png"),
    BQUEEN_ICON(".\\src\\images\\blackQueen.png"),
    BROOK_ICON(".\\src\\images\\blackRook.png");
    
    private final String icon;
    
    private IconEnum(String icon){
        this.icon = icon;
    }
    public String getIcon(){
        return this.icon;
    }
}
