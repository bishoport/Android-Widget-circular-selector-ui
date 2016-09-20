

<h1>Circular Selector UI</h1>

<p>This widget displays a counter. It fits all sizes.</p>



<img src="http://www.bishoport.net/git/images/multiple.png" alt="alt text" width="300">
<img src="http://www.bishoport.net/git/images/single.png" alt="alt text" width="300">

<p>The use is very simple:</p>
<p><b>In the layout</b></p>

```xml
    <com.bishoport.ui.CircularLines
        android:id="@+id/circularLines"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </com.bishoport.ui.CircularLines>
```
<p><b>In the Activity/Fragment</b></p>

<p>You must implement the interface OnSetNumberCircularLinesListener in the owner file to listen the return number.</p>
   
    public class SelectorActivity extends AppCompatActivity implements CircularLines.OnSetNumberCircularLinesListener{}
    
<p>This is the only interface function </p>
    void onSetNumberCircularLines(String value);
    
    
<p>In onCreate function</p>
    CircularLines circularLines = (CircularLines) findViewById(R.id.circularLines);
                  circularLines.setListener(this);
                  circularLines.setiMaximumValue(40);
                  circularLines.setInitialValue("20");
        
<p>The API is very simple</p>
    public void setListener(OnSetNumberCircularLinesListener listener)
    public void setInitialValue(String value)
    public void setiMaximumValue(int iNumberOfLines)
