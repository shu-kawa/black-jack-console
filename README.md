# ブラックジャック（コンソール版）

試作品  
コンソール上で動きます.

## 動作確認済み環境

Windows 11 64bit  
Amazon Corretto 21.0.6  
Gradle 8.13  
IntelliJ IDEA 2025.1.2 (Community Edition)  

## 動作方法

依存関係を特に作っていないため、IDEの実行ボタンでも動くはず。  
ダメなら`./gradlew clean build run`で動きます、多分。

## TODO

* [ ] コンソール出力時の文字化け解消  
  どうせおま環なのでほぼ諦めている  
　  
* [ ] パッケージ配置の見直し  
  deckとplayerパッケージと言う構成自体違う気がする  
  パッケージ間のやり取りはHandインスタンスを経由しているので、カプセル化しきれていないような  
　  
* [ ] ビジネスロジックの修正  
  単一責任の原則を逸脱したクラスがある  
  `Participant#getHandScore`と`Participant#isBust`とか大丈夫？？

## 補足

ルール説明やパッケージ構成のリファクタリングなどに無料プランのChatGPTを利用しています。
