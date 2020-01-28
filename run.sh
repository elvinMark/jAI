cd src
javac -d ../build/ com/neural/*.java
javac -d ../build/ com/math/*.java
javac -d ../build/ com/test/Test.java
cd ..
cd build
java com.test.Test
