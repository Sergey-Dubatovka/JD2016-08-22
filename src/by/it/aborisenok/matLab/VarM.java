package by.it.aborisenok.matLab;

import java.util.Arrays;

/**
 * Created by Лёша on 09.09.2016.
 */
public class VarM extends Var {


    public double[][] value;
    public VarM(double[][] value) {
        int rows=value.length;
        int cols=value[0].length;
        this.value=new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.value[i][j]=value[i][j];
            }
        }
    }


    public double[][] getValue() {
        return value;
    }
//******************************************
    @Override
    public Var add(VarF v) {
        VarM res = new VarM(value);
        int rows = value.length;
        int cols = value[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.value[i][j ] += + v.getValue();
            }
        }
        return res;
    }

    public Var add(VarM v) {
        VarM res = new VarM(value);
        int rows=value.length;
        int cols=value[0].length;
        if (v.getValue().length == rows && v.getValue()[0].length == cols){
            for (int i = 0; i < rows; i++){
                for (int j = 0; j <cols; j++){
                    res.value[i][j] += v.getValue()[i][j];
                }
            }
            return res;
        } else {
            Log.print("Разные размеры матриц");
            return null;
        }
    }
//******************************************
    @Override
    public Var sub(VarF v) {
        VarM res = new VarM(value);
        int rows = value.length;
        int cols = value[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.value[i][j ] -= v.getValue();
            }
        }
        return res;
    }

    @Override
    public Var sub(VarM v) {
        VarM res = new VarM(value);
        int rows=value.length;
        int cols=value[0].length;
        if (v.getValue().length == rows && v.getValue()[0].length == cols){
            for (int i = 0; i < rows; i++){
                for (int j = 0; j <cols; j++){
                    res.value[i][j] -= v.getValue()[i][j];
                }
            }
            return res;
        } else {
            Log.print("Разные размеры матриц");
            return null;
        }
    }
//******************************************
    @Override
    public Var mul(VarF v) {
        VarM res = new VarM(value);
        int rows = value.length;
        int cols = value[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.value[i][j ] *= + v.getValue();
            }
        }
        return res;
    }

    @Override
    public Var mul(VarV v) {
        VarV res = new VarV(new double[value.length]);
        if (value.length == value[0].length &&
                value.length == v.getValue().length) {
            for (int i = 0; i < value.length; i++){
                for (int j = 0; j < v.getValue().length; j++)
                    res.getValue()[i] = res.getValue()[i] + value[i][j]*v.getValue()[j];
            }
            return res;
        } else {
            Log.print("Некорректные введённые данные");
            return null;
        }
    }

    @Override
    public Var mul(VarM v) {
        VarM res = new VarM(new double[value.length][value[0].length]);
        int firstMatrixRows = value.length;
        int firstMatrixCols = value[0].length;
        int secondMatrixRows = v.getValue().length;
        int secondMatrixCols = v.getValue()[0].length;

        if (firstMatrixRows == secondMatrixRows && firstMatrixCols == secondMatrixRows
                && firstMatrixRows == firstMatrixCols) {
                for (int i = 0; i < value.length; i++) {
                    for (int j = 0; j < v.getValue()[0].length; j++) {
                        for (int k = 0; k < v.getValue().length; k++) {
                            res.getValue()[i][j] = res.getValue()[i][j] + value[i][k] * v.getValue()[k][j];
                        }
                    }
                }
            return res;
        } else {
            Log.print("Заданные неверные размеры матриц");
            return res;
        }
    }
//***********************************

    @Override
    public Var div(VarF v) {
        VarM res = new VarM(value);
        int rows = value.length;
        int cols = value[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res.value[i][j ] /= + v.getValue();
            }
        }
        return res;
    }

    @Override
    public String toString() {
        String res="[";
        String delimiter="";
        for (double[] v:value) {
            res=res+delimiter+Arrays.toString(v);
            delimiter=",";
        }
        res=res+"]";
        return res;
    }
}