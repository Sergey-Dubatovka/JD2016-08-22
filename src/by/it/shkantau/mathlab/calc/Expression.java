package by.it.shkantau.mathlab.calc;

import by.it.shkantau.mathlab.calc.Operand.Var;
import by.it.shkantau.mathlab.calc.exceptions.MathLabException;
import by.it.shkantau.mathlab.util.parser.Parser;

import java.util.List;

public class Expression {

    private final String expression;
    private List <Var> vars;
    private List<String>operators;
    private Var result;


    public Expression(String expression) {
        this.expression = expression;
    }

    public void parse() throws MathLabException {
        String[] strings = Parser.splitExpressionAndName(expression);
        String resultName = strings[0];
        String expressionStr = strings[1];
        vars = Parser.parseStringToVarList(expressionStr);
        operators = Parser.parseStringToOperatorList(expressionStr);
        if(vars.size() != operators.size()+1){
            throw new MathLabException("operators count="+ operators.size() + " don't match vars count="+vars.size());
        }
    }

    public void calc() throws MathLabException {
        while (operators.size() != 0){
            oneOperation(getMaxPriorityIndex());
        }
        result = vars.get(0);
    }

    private void oneOperation(int operatorIndex) throws MathLabException {

        String operatorStr= operators.get(operatorIndex);
        Var result;
        Var one = vars.get(operatorIndex);
        assert one != null;
        Var two=vars.get(operatorIndex+1);
        assert two != null;
        switch (operatorStr){
            case "+": result=one.add(two); break;
            case "-": result=one.sub(two); break;
            case "*": result=one.mul(two); break;
            case "/": result=one.div(two); break;
            default: throw new MathLabException("unsupported operation Exception " + operatorStr);
        }

        if (result == null){
            throw new MathLabException("cant calculate: " + one + " " + operatorStr + " " +two);
        }

        vars.remove(operatorIndex);
        operators.remove(operatorIndex);
        vars.set(operatorIndex, result);
    }

    private int getMaxPriorityIndex() throws MathLabException {
        int res = -1;
        for (int i = 0; i < operators.size(); i++) {
            if(getPriority(operators.get(i)) > res){
                res = i;
            }
        }
        return res;
    }

    private int getPriority(String operator) throws MathLabException {
        int res = -1;
        for (int i = 0; i < Parser.priorityOperator.length; i++) {
            if (Parser.priorityOperator[i].equals(operator)){
                res = i;
            }
        }
        if (res < 0){
            throw new MathLabException("wrong operator");
        }
        return res;
    }

    public Var getResult() {
        return result;
    }
}
