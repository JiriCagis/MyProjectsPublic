<h1> Disk Speed </h1>
It is a terminal utility for detections your average disk speed. An algorithm writes dummmy data on a test disk and it measures time for write. The utility is simple for use, you only put this file on disk where you want measure speed and you write to a console:
<pre>
python disk_speed.py
</pre>
Without parameter utility works with default values, it writes 1MB block 50x to disk and it calculates speed. You can add parameters too, it use following form:
<pre>
python disk_speed [size block] [unit | K,M,G] [count block]
</pre>
