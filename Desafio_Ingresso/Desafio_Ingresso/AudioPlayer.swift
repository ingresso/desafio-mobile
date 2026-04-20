//
//  AudioPlayer.swift
//  Desafio_Ingresso
//
//  Created by Guilherme Vassallo on 14/09/21.
//

import AVFoundation

class AudioPlayer {

  static var player:AVAudioPlayer?

    static func playSound(soundfile: String, vol: Float) {

      if let path = Bundle.main.path(forResource: soundfile, ofType: nil){

          do{

              player = try AVAudioPlayer(contentsOf: URL(fileURLWithPath: path))
              player?.prepareToPlay()
              player?.volume = vol
              player?.play()

          }catch {
              print("Error")
          }
      }
   }
}
