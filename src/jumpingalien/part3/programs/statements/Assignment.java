package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;
import jumpingalien.part3.programs.exceptions.TypeError;

//TODO getters en setters !
public class Assignment extends Statement {

	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		if((variableType instanceof jumpingalien.part3.programs.types.Boolean && !ReturnTypeDetection.returnsBoolean(value)) 
				||(variableType instanceof jumpingalien.part3.programs.types.GameObject && !ReturnTypeDetection.returnsObject(value)) 
				||(variableType instanceof jumpingalien.part3.programs.types.Double && !ReturnTypeDetection.returnsDouble(value))
				||(variableType instanceof jumpingalien.part3.programs.types.Direction && !ReturnTypeDetection.returnsDirection(value))){
			throw new TypeError(sourceLocation);
		}
		this.variableType = variableType;
		this.value = value;
	}
	
	private String variableName;
	private Type variableType;
	private Expression value;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			program.getDeclarationVariables().put(variableName, value.getValue(program));
			//TODO getters en setters !!
		}
	}
}
