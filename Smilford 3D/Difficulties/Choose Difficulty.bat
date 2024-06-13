@ echo off
set mypath=%~dp0
@echo %mypath%
:ask
set /P diff="Select your difficulty: (C)lassic | (H)ard | (N)ightmare: "
IF %diff% == C START %mypath%\Game\Smilford3D.exe & EXIT
IF %diff% == H START %mypath%\Game\smilfordhard.exe & EXIT
IF %diff% == N START %mypath%\Game\nightmare.exe & EXIT
goto :ask
PAUSE