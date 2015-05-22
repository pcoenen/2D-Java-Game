package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class GetWidth extends Expression {

	public GetWidth(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression= expression;

	}

	private Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;
	
	@Override
	public java.lang.Double getValue(Program program) {
		if( getExpression().getValue(program) instanceof GameObject){
			GameObject object = (GameObject) getExpression().getValue(program);
			return (double) object.getCurrentSprite().getWidth();
		} else if (getExpression().getValue(program) instanceof Tile) {
			return (double) program.getObject().getWorld().getTileSize();
		} else {
			program.setLinesToRun(0);
			program.setHasAnError(true);
			return null;
		}
	}

}
