AppTitle "Smilford 3D! By KABX - Beta"
; (c) 2022 KerbalABX
; Music and textures by KerbalABX

Graphics3D 800,600,16,2

Const CUBE_COL=1
Const SPHERE_COL=2
Const PU_COL=3
Const PLANE_COL=4
Const WALL_COL=5

fntArial=LoadFont("Arial",20,False,False,False)
fntArialB=LoadFont("Arial",32,True,True,True)

SetBuffer BackBuffer()

camera=CreateCamera()
CameraViewport camera,0,0,800,600
PositionEntity camera,0,5,-5
RotateEntity camera,30,0,0

light=CreateLight()

SeedRnd MilliSecs()

;powerup
pux = Rnd(-8,8)
puy = Rnd(20,40)
puz = Rnd(14,3)

pu=CreateCube()
EntityColor pu,0,255,0
ScaleEntity pu,0.5,0.5,0.5
PositionEntity pu,pux,puy,puz
EntityType pu,PU_COL
EntityRadius pu,1

;powerup marker
pm=CreateCube()
EntityColor pm,0,255,0
EntityAlpha pm,0.25
ScaleEntity pm,0.1,50,0.1
PositionEntity pm,pux,0,puz


;walls
wallback=CreateCube()
PositionEntity wallback,0,0,16
ScaleEntity wallback,14,5,1.5
EntityType wallback,WALL_COL
EntityBox wallback,-14,0,0,28,5,-1
brick=LoadTexture("media\tex\brick.bmp")
ScaleTexture brick,0.1,0.3
EntityTexture wallback,brick

wallright=CreateCube()
PositionEntity wallright,15,0,0
ScaleEntity wallright,1,5,15
EntityType wallright,WALL_COL
EntityBox wallright,0,0,-15,-1,5,30
EntityTexture wallright,brick

wallleft=CreateCube()
PositionEntity wallleft,-15,0,0
ScaleEntity wallleft,1,5,15
EntityType wallleft,WALL_COL
EntityBox wallleft,0,0,-15,1.5,5,30
EntityTexture wallleft,brick

wallfront=CreateCube()
PositionEntity wallfront,0,0,-5
EntityType wallfront,WALL_COL
EntityBox wallfront,-15,0,3,30,1,1


;cube
cube=CreateCube()
PositionEntity cube,-5,0,6
RotateEntity cube,Rnd(360),Rnd(360),Rnd(360)
EntityColor cube,70,80,190
EntityType cube,CUBE_COL
EntityRadius cube,0.5

;smilford
sphere=CreateCone(12)
PositionEntity sphere,5,0,5
EntityType sphere,SPHERE_COL
texture=LoadTexture("media\tex\pyrad.bmp")
EntityTexture sphere,texture
EntityRadius sphere,1

;ground
plane=CreatePlane()
PositionEntity plane,0,-1,0
EntityType plane,PLANE_COL
texture2=LoadTexture("media\tex\wood.bmp")
EntityTexture plane,texture2

;sounds and music
bckgrndmsc=LoadSound("media\sounds\iron.wav")
colsnd=LoadSound("media\sounds\munchlow.wav")
pusnd=LoadSound("media\sounds\bell.wav")
miss=LoadSound("media\sounds\missed.wav")

Global score=0

Collisions SPHERE_COL,CUBE_COL,3,1
Collisions SPHERE_COL,PU_COL,3,1
Collisions PU_COL,PLANE_COL,2,1
Collisions PU_COL,SPHERE_COL,2,1
Collisions SPHERE_COL,WALL_COL,3,3

LoopSound bckgrndmsc
SoundVolume bckgrndmsc,1
PlaySound bckgrndmsc




While Not KeyDown(1)

	TranslateEntity pu,0,-0.03,0
	TurnEntity pu,1,-1,0
	TurnEntity pm,0,-1,0

		If KeyDown(200) Then
			MoveEntity sphere,0,0,0.1
		EndIf

		If KeyDown(203) Then
			TurnEntity sphere,0,3,0
		EndIf

		If KeyDown(205) Then
			TurnEntity sphere,0,-3,0
		EndIf
	
		If KeyDown(19) Then
			PositionEntity sphere,5,0,5
		EndIf

	
	UpdateWorld
	RenderWorld

	If EntityCollided(sphere,CUBE_COL) Then
		Text 370,80,"Munch!"
		PositionEntity cube,Rnd(-8,8),0,Rnd(15,3)
		RotateEntity cube,Rnd(360),Rnd(360),Rnd(360)
		PlaySound(colsnd)
		score=score+1
	EndIf

	If EntityCollided(sphere,PU_COL) Then
		score=score+10
		PositionEntity pu,Rnd(-8,8),Rnd(30,40),Rnd(15,3)
		PlaySound(pusnd)
	EndIf
	
	pux = Rnd(-8,8)
	puy = Rnd(20,40)
	puz = Rnd(15,3)
	
	If EntityCollided(pu,SPHERE_COL) Then
		score=score+10
		PositionEntity pu,pux,puy,puz
		PositionEntity pm,pux,0,puz
		PlaySound(pusnd)
	EndIf
	
	If EntityCollided(pu,PLANE_COL) Then
		PlaySound(miss)
		PositionEntity pu,pux,puy,puz
		PositionEntity pm,pux,0,puz
		score=score-5
	EndIf
	
	If score <= -1 Then
	 GameOver()
	EndIf
	
	If score = 500 Then
		GameWin()
	EndIf
	If score = 501 Then
		GameWin()
	EndIf
	If score = 502 Then
		GameWin()
	EndIf
	If score = 503 Then
		GameWin()
	EndIf
	If score = 504 Then
		GameWin()
	EndIf
	If score = 505 Then
		GameWin()
	EndIf
	If score = 506 Then
		GameWin()
	EndIf
	If score = 507 Then
		GameWin()
	EndIf
	If score = 508 Then
		GameWin()
	EndIf
	If score = 509 Then
		GameWin()
	EndIf




	
	SetFont fntArial
	Text 335,500,"Score: " + score
	Text 50,50,"Controls: left and right arrows to turn, up arrow to accelerate. Lost? Use R to reset Smilford."
	Text 50,10,"Green boxes give +10 score, but if they hit the ground you lose 5 score."
	Text 50,30,"If your score goes below 0 it's game over, but if you get above 500, you win and can keep going!"
	Flip



Wend

Function MainGame()

End Function

Function GameWin()

	fntArialB=LoadFont("Arial",32,True,True,True)
	SetFont fntArialB
	Text 240,300,"You got to 500! Here's a bonus!"
	score=score+50
	Flip
	Delay(5000)	

End Function

Function GameOver()
	
	fntArialB=LoadFont("Arial",32,True,True,True)
	SetFont fntArialB
	Text 320,240,"Game over!!!"
	Flip
	Delay(5000)

End Function
End