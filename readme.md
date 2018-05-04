####项目简介
- 该项目是一个简单的表格View
- 仅仅是提供了表格的绘制

---
表格控件 **CombainTableView**

![tableView](https://github.com/yudapiandroid/tableview/blob/master/images/table_view.jpg)

图中红色标出的一个方块称为一个 cell

---

#使用说明

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
	        compile 'com.github.yudapiandroid:tableview:0.0.4'
	}
```

声明控件
```xml
    <com.suse.dapi.tableview.core.view.CombainTableView
        android:id="@+id/table_view"
        app:c_cell_width="20dp"
        app:c_cell_height="20dp"
        app:c_border_width="1dp"
        app:c_border_color="#ff0"
        app:c_bg_color="#fff"
        app:c_use_cell_fix="true"
        app:c_use_open_gl="false"
        android:layout_width="match_parent"
        android:layout_height="104dp">
    </com.suse.dapi.tableview.core.view.CombainTableView>
```

#属性说明
- c_bg_color 背景颜色
- c_border_color 表格的颜色
- c_border_width 表格的宽度
- c_row 表格行数
- c_column 表格列数
- c_cell_width 表格宽度
- c_cell_height 表格高度
- c_use_cell_fix 表格的测量模式
    - true 如果是true则需要指定 c_cell_width 和 c_cell_height ,表格的列数和行数会自动计算
    - false 如果是false 则需要指定 c_row 和 c_column ，cell的宽度和高度会自动计算
- c_use_open_gl 绘制的时候是否使用opengl来绘制 这个在多次添加大量数据后有拉伸的情况


---

控件的宽度和高度计算

- 指定cell宽度 和 高度 <br/>
  （ cell宽度 + border宽度 ） * 想要的列数
  （ cell高度 + border高度 ） * 想要的行数


---

#Layer说明
```java
        public interface DrawLayer {

            /**
             *
             * 一个是支持 原生Canvas的 一个是支持 Opengl的
             *
             * @param data
             * @param rect
             * @param canvas
             * @param gl
             */
            void draw(Object data, Rect rect,Canvas canvas, ICanvasGL gl);

        }
```

由于使用了两种实现 一种是原生的实现 一种是opengl的实现 所以有两个 canvas
使用的时候用非空来判断使用
```java
@Override
    public void draw(Object data, Rect rect, Canvas canvas, ICanvasGL gl) {

        if(data == null){
            return;
        }

        int res = convertObjToResource(data);
        if(res < 0){
            return;
        }
        Bitmap bitmap = BitmapUtils.loadBitmap(res, context, rect, padding);
        if(bitmap == null){
            return;
        }

        // 绘制
        if(canvas != null){
            drawByCanvas(bitmap,rect,padding,canvas);
        }
        if(gl != null){
            drawByGL(bitmap,rect,padding,gl);
        }
    }
```

#示例代码

```java
        tableView.addDrawLayer(new TypeBitmapLayer(1,this));
        tableView.setData(models);
```