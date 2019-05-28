package simpleTunes;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map.Entry;

import javafx.scene.paint.Color;


	/**
	 * The Class sets all the colors which are used
	 * to set the color of all the shapes.
	 * 
	 * @author Roland
	 *
	 */

public class NamedColors {
	private static HashMap<String, Color> namedColors = createNamedColors();

	
	/**
	 * Reurns the named color from a hashmap
	 * 
	 * @param name name of the color
	 * @return color
	 */
	
	
	public static Color get(String name) {
		return namedColors.get(name);
	}

	private static HashMap<String, Color> createNamedColors() {
		HashMap<String, Color> colors = new HashMap<String, Color>(25);

		colors.put("Orange1", Color.rgb(255, 192, 115));
		colors.put("Orange2", Color.rgb(245, 167, 72));
		colors.put("Orange3", Color.rgb(234, 140, 26));
		colors.put("Orange4", Color.rgb(185, 106, 10));
		colors.put("Orange5", Color.rgb(145, 80, 0));

		colors.put("Red1", Color.rgb(227, 132, 132));
		colors.put("Red2", Color.rgb(212, 106, 106));
		colors.put("Red3", Color.rgb(170, 57, 57));
		colors.put("Red4", Color.rgb(128, 21, 21));
		colors.put("Red5", Color.rgb(85, 0, 0));

		colors.put("Purple1", Color.rgb(160, 100, 155));
		colors.put("Purple2", Color.rgb(144, 72, 138));
		colors.put("Purple3", Color.rgb(116, 39, 109));
		colors.put("Purple4", Color.rgb(87, 14, 81));
		colors.put("Purple5", Color.rgb(58, 0, 53));

		colors.put("Blue1", Color.rgb(97, 97, 174));
		colors.put("Blue2", Color.rgb(71, 71, 158));
		colors.put("Blue3", Color.rgb(45, 45, 146));
		colors.put("Blue4", Color.rgb(29, 29, 116));
		colors.put("Blue5", Color.rgb(12, 12, 88));

		colors.put("Green1", Color.rgb(120, 191, 120));
		colors.put("Green2", Color.rgb(82, 170, 82));
		colors.put("Green3", Color.rgb(45, 138, 45));
		colors.put("Green4", Color.rgb(18, 105, 18));
		colors.put("Green5", Color.rgb(0, 71, 0));

		return colors;
	}
	
	/*
	 * Returns the name of the incoming color object
	 * 
	 */

	public static String getColorString(Color color) {

		for (Entry<String, Color> entry : namedColors.entrySet()) {

			if (Objects.equals(color, entry.getValue())) {
				return entry.getKey();
			}

		}

		return null;

	}
}
