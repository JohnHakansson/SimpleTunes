package simpleTunes;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;

import javafx.scene.paint.Color;

public class NamedColors {
	private static HashMap<String, Color> namedColors = createNamedColors();
	
	 public static Color get(String name) {
         return namedColors.get(name);
     }

     private static HashMap<String, Color> createNamedColors() {
         HashMap<String, Color> colors = new HashMap<String,Color>(256);

         colors.put("aliceblue",            Color.ALICEBLUE);
         colors.put("antiquewhite",         Color.ANTIQUEWHITE);
         colors.put("aqua",                 Color.AQUA);
         colors.put("aquamarine",           Color.AQUAMARINE);
         colors.put("azure",                Color.AZURE);
         colors.put("beige",                Color.BEIGE);
         colors.put("bisque",               Color.BISQUE);
         colors.put("black",                Color.BLACK);
         colors.put("blanchedalmond",       Color.BLANCHEDALMOND);
         colors.put("blue",                 Color.BLUE);
         colors.put("blueviolet",           Color.BLUEVIOLET);
         colors.put("brown",                Color.BROWN);
         colors.put("burlywood",            Color.BURLYWOOD);
         colors.put("cadetblue",            Color.CADETBLUE);
         colors.put("chartreuse",           Color.CHARTREUSE);
         colors.put("chocolate",            Color.CHOCOLATE);
         colors.put("coral",                Color.CORAL);
         colors.put("cornflowerblue",       Color.CORNFLOWERBLUE);
         colors.put("cornsilk",             Color.CORNSILK);
         colors.put("crimson",              Color.CRIMSON);
         colors.put("cyan",                 Color.CYAN);
         colors.put("darkblue",             Color.DARKBLUE);
         colors.put("darkcyan",             Color.DARKCYAN);
         colors.put("darkgoldenrod",        Color.DARKGOLDENROD);
         colors.put("darkgray",             Color.DARKGRAY);
         colors.put("darkgreen",            Color.DARKGREEN);
         colors.put("darkgrey",             Color.DARKGREY);
         colors.put("darkkhaki",            Color.DARKKHAKI);
         colors.put("darkmagenta",          Color.DARKMAGENTA);
         colors.put("darkolivegreen",       Color.DARKOLIVEGREEN);
         colors.put("darkorange",           Color.DARKORANGE);
         colors.put("darkorchid",           Color.DARKORCHID);
         colors.put("darkred",              Color.DARKRED);
         colors.put("darksalmon",           Color.DARKSALMON);
         colors.put("darkseagreen",         Color.DARKSEAGREEN);
         colors.put("darkslateblue",        Color.DARKSLATEBLUE);
         colors.put("darkslategray",        Color.DARKSLATEGRAY);
         colors.put("darkslategrey",        Color.DARKSLATEGREY);
         colors.put("darkturquoise",        Color.DARKTURQUOISE);
         colors.put("darkviolet",           Color.DARKVIOLET);
         colors.put("deeppink",             Color.DEEPPINK);
         colors.put("deepskyblue",          Color.DEEPSKYBLUE);
         colors.put("dimgray",              Color.DIMGRAY);
         colors.put("dimgrey",              Color.DIMGREY);
         colors.put("dodgerblue",           Color.DODGERBLUE);
         colors.put("firebrick",            Color.FIREBRICK);
         colors.put("floralwhite",          Color.FLORALWHITE);
         colors.put("forestgreen",          Color.FORESTGREEN);
         colors.put("fuchsia",              Color.FUCHSIA);
         colors.put("gainsboro",            Color.GAINSBORO);
         colors.put("ghostwhite",           Color.GHOSTWHITE);
         colors.put("gold",                 Color.GOLD);
         colors.put("goldenrod",            Color.GOLDENROD);
         colors.put("gray",                 Color.GRAY);
         colors.put("green",                Color.GREEN);
         colors.put("greenyellow",          Color.GREENYELLOW);
         colors.put("grey",                 Color.GREY);
         colors.put("honeydew",             Color. HONEYDEW);
         colors.put("hotpink",              Color.HOTPINK);
         colors.put("indianred",            Color.INDIANRED);
         colors.put("indigo",               Color.INDIGO);
         colors.put("ivory",                Color.IVORY);
         colors.put("khaki",                Color.KHAKI);
         colors.put("lavender",             Color.LAVENDER);
         colors.put("lavenderblush",        Color.LAVENDERBLUSH);
         colors.put("lawngreen",            Color.LAWNGREEN);
         colors.put("lemonchiffon",         Color.LEMONCHIFFON);
         colors.put("lightblue",            Color.LIGHTBLUE);
         colors.put("lightcoral",           Color.LIGHTCORAL);
         colors.put("lightcyan",            Color.LIGHTCYAN);
         colors.put("lightgoldenrodyellow", Color.LIGHTGOLDENRODYELLOW);
         colors.put("lightgray",            Color.LIGHTGRAY);
         colors.put("lightgreen",           Color.LIGHTGREEN);
         colors.put("lightgrey",            Color.LIGHTGREY);
         colors.put("lightpink",            Color.LIGHTPINK);
         colors.put("lightsalmon",          Color.LIGHTSALMON);
         colors.put("lightseagreen",        Color.LIGHTSEAGREEN);
         colors.put("lightskyblue",         Color.LIGHTSKYBLUE);
         colors.put("lightslategray",       Color.LIGHTSLATEGRAY);
         colors.put("lightslategrey",       Color.LIGHTSLATEGREY);
         colors.put("lightsteelblue",       Color.LIGHTSTEELBLUE);
         colors.put("lightyellow",          Color.LIGHTYELLOW);
         colors.put("lime",                Color.LIME);
         colors.put("limegreen",            Color.LIMEGREEN);
         colors.put("linen",                Color.LINEN);
         colors.put("magenta",              Color.MAGENTA);
         colors.put("maroon",               Color.MAROON);
         colors.put("mediumaquamarine",     Color.MEDIUMAQUAMARINE);
         colors.put("mediumblue",           Color.MEDIUMBLUE);
         colors.put("mediumorchid",         Color.MEDIUMORCHID);
         colors.put("mediumpurple",         Color.MEDIUMPURPLE);
         colors.put("mediumseagreen",       Color.MEDIUMSEAGREEN);
         colors.put("mediumslateblue",      Color.MEDIUMSLATEBLUE);
         colors.put("mediumspringgreen",    Color.MEDIUMSPRINGGREEN);
         colors.put("mediumturquoise",      Color.MEDIUMTURQUOISE);
         colors.put("mediumvioletred",      Color.MEDIUMVIOLETRED);
         colors.put("midnightblue",         Color.MIDNIGHTBLUE);
         colors.put("mintcream",            Color.MINTCREAM);
         colors.put("mistyrose",            Color.MISTYROSE);
         colors.put("moccasin",             Color.MOCCASIN);
         colors.put("navajowhite",          Color.NAVAJOWHITE);
         colors.put("navy",                 Color.NAVY);
         colors.put("oldlace",              Color.OLDLACE);
         colors.put("olive",                Color.OLIVE);
         colors.put("olivedrab",            Color.OLIVEDRAB);
         colors.put("orange",               Color.ORANGE);
         colors.put("orangered",            Color.ORANGERED);
         colors.put("orchid",               Color.ORCHID);
         colors.put("palegoldenrod",        Color.PALEGOLDENROD);
         colors.put("palegreen",            Color.PALEGREEN);
         colors.put("paleturquoise",        Color.PALETURQUOISE);
         colors.put("palevioletred",        Color.PALEVIOLETRED);
         colors.put("papayawhip",           Color.PAPAYAWHIP);
         colors.put("peachpuff",            Color.PEACHPUFF);
         colors.put("peru",                 Color.PERU);
         colors.put("pink",                 Color.PINK);
         colors.put("plum",                 Color.PLUM);
         colors.put("powderblue",           Color.POWDERBLUE);
         colors.put("purple",               Color.PURPLE);
         colors.put("red",                  Color.RED);
         colors.put("rosybrown",            Color.ROSYBROWN);
         colors.put("royalblue",            Color.ROYALBLUE);
         colors.put("saddlebrown",          Color.SADDLEBROWN);
         colors.put("salmon",               Color.SALMON);
         colors.put("sandybrown",           Color.SANDYBROWN);
         colors.put("seagreen",             Color.SEAGREEN);
         colors.put("seashell",             Color.SEASHELL);
         colors.put("sienna",               Color.SIENNA);
         colors.put("silver",               Color.SILVER);
         colors.put("skyblue",              Color.SKYBLUE);
         colors.put("slateblue",            Color.SLATEBLUE);
         colors.put("slategray",            Color.SLATEGRAY);
         colors.put("slategrey",            Color.SLATEGREY);
         colors.put("snow",                 Color.SNOW);
         colors.put("springgreen",          Color.SPRINGGREEN);
         colors.put("steelblue",            Color.STEELBLUE);
         colors.put("tan",                  Color.TAN);
         colors.put("teal",                 Color.TEAL);
         colors.put("thistle",              Color.THISTLE);
         colors.put("tomato",               Color.TOMATO);
         colors.put("transparent",          Color.TRANSPARENT);
         colors.put("turquoise",            Color.TURQUOISE);
         colors.put("violet",               Color.VIOLET);
         colors.put("wheat",                Color.WHEAT);
         colors.put("white",                Color.WHITE);
         colors.put("whitesmoke",           Color.WHITESMOKE);
         colors.put("yellow",               Color.YELLOW);
         colors.put("yellowgreen",          Color.YELLOWGREEN);

         return colors;
     }
     
     public static String getColorString(Color color) {
    	 
    	 for(Entry<String, Color> entry : namedColors.entrySet()) {
    		 
    		 if(Objects.equals(color, entry.getValue())) {
    			 return entry.getKey();
    		 }
    		 
    	 }
    	 
    	 return null;
    	 
     }

}
