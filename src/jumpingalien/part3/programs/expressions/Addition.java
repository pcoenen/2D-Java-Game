package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.IllegalMatchingTypeException;

public class Addition extends BinaryOperator {
	
	public Addition(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
		if(!returnsDouble(leftOperand, rightOperand)){
			throw new IllegalMatchingTypeException(sourceLocation);
		}
	}
	
	@Override
	public java.lang.Double getValue(Program program) {
		double left = (double) getLeftOperand().getValue(program);
		double right = (double) getRightOperand().getValue(program);
		return new java.lang.Double(left+right);
	}
}
