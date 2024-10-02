/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package master_detail;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gonzalo
 */
public class ListTableModel<T> extends AbstractTableModel {

    private List<T> data;
    private List<TableColum<T, ?>> columns;

    public ListTableModel() {
        this.columns = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    public ListTableModel(List<TableColum<T, ?>> columns) {
        this.columns = columns;
        this.data = new ArrayList<>();
    }

    public ListTableModel(List<TableColum<T, ?>> columns, List<T> data) {
        this.data = data;
        this.columns = columns;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int column) {
        return columns.get(column).getColumName();
    }

    @Override
    public int findColumn(String columnName) {
        OptionalInt result = IntStream.range(0, columns.size())
                .filter(x -> columns.get(x).getColumName().equals(columnName))
                .findFirst();

        if (result.isPresent()) {
            return result.getAsInt();
        }

        return -1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex).getColumType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).getDataGetter().apply(data.get(rowIndex));
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TableColum<T, Object> col = (TableColum<T, Object>) columns.get(columnIndex);
        col.getDataSetter().accept(data.get(rowIndex), col.getColumType().cast(aValue));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).isEditable();
    }

    public static class TableColum<T, R> {

        private final String columName;
        private final Class<R> columType;
        private final Function<T, R> dataGetter;
        private final BiConsumer<T, R> dataSetter;
        private boolean editable;

        public TableColum(String columName, Class<R> columType, Function<T, R> dataGetter, BiConsumer<T, R> dataSetter) {
            this.columName = columName;
            this.columType = columType;
            this.dataGetter = dataGetter;
            this.dataSetter = dataSetter;
            this.editable = true;
        }

        public TableColum(String columName, Class<R> columType, Function<T, R> dataGetter, BiConsumer<T, R> dataSetter, boolean editable) {
            this(columName, columType, dataGetter, dataSetter);
            this.editable = false;
        }

        public String getColumName() {
            return columName;
        }

        public Class<R> getColumType() {
            return columType;
        }

        public Function<T, R> getDataGetter() {
            return dataGetter;
        }

        public BiConsumer<T, R> getDataSetter() {
            return dataSetter;
        }

        public boolean isEditable() {
            return editable;
        }

    }
}
