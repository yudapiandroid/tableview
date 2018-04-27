####项目简介
- 该项目是一个简单的表格View
- 仅仅是提供了表格的绘制

---
表格控件 **CombainTableView**

![tableView](https://github.com/yudapiandroid/tableview/blob/master/images/table_view.jpg)
图中红色标出的一个方块称为一个 cell

自定义属性
- 背景颜色 c_bg_color
- 分割线颜色 c_border_color
- 分割线大小 c_border_width
- cell宽度 c_cell_width
- cell高度 c_cell_height
- 需要显示的行数 c_row
- 需要显示的列数 c_column

```xml
    <declare-styleable name="CombainTableView">
        <attr name="c_bg_color" format="color"/>
        <attr name="c_border_color" format="color"/>
        <attr name="c_border_width" format="dimension"/>
        <attr name="c_row" format="integer"/>
        <attr name="c_column" format="integer"/>
        <attr name="c_cell_width" format="dimension"/>
        <attr name="c_cell_height" format="dimension"/>
    </declare-styleable>
```

---
自定义绘制的内容
通过实现 DrawLayer 来实现内容的绘制
```java
public interface DrawLayer {
    void draw(Object data,Rect rect, Canvas canvas);
}
```

- data 是当前cell对应的数据
- rect 是当前的可绘制区域
- canvas 是回调onDraw的时候的canvas

有一个自带的实现，DrawBitmapLayer

---
使用说明

配置仓库地址
```xml
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```

添加依赖
```xml
dependencies {
	        compile 'com.github.yudapiandroid:tableview:0.0.2'
	}
```

声明控件
```xml
    <com.suse.dapi.tableview.core.view.CombainTableView
        android:id="@+id/table"
        app:c_bg_color="#fff"
        app:c_border_color="#eee"
        app:c_border_width="1dp"
        app:c_column="10"
        app:c_row="10"
        android:layout_width="300dp"
        android:layout_height="300dp">
    </com.suse.dapi.tableview.core.view.CombainTableView>
```

绑定数据
```java
        tableView = (CombainTableView) findViewById(R.id.table);
        data = new ArrayList<>();
        for (int i=0;i < 5;i++){
            data.add(new Object());
        }
        tableView.addDrawLayer(new DrawBitmapLayer(
                BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),
                2
        ));
        tableView.setData(data);
```
