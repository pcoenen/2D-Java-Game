package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsDead extends Expression {

	public IsDead(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}
	
	private Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Object getValue(Program program) {
		if(expression.getValue(program) instanceof GameObject){
			return ((GameObject) expression.getValue(program)).isDead();
		}
		return false;
	}

}