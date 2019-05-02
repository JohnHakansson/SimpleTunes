package simpleTunes;

import java.io.Serializable;

public class RemoveShapeMessage implements Serializable{
	private static final long serialVersionUID = 6117382832070187915L;
	private MusicShape shape;
	
	public RemoveShapeMessage(MusicShape shape) {
		this.shape = shape;
	}
	
	public MusicShape getShape() {
		return shape;
	}
}
