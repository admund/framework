cd TexturePacker

java -cp gdx.jar;gdx-tools.jar com.badlogic.gdx.tools.texturepacker.TexturePacker ../all ../ all.atlas

cd ..

copy *.png ..\android\assets\
copy *.atlas ..\android\assets\

PAUSE