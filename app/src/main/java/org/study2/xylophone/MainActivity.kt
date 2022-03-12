package org.study2.xylophone

import android.content.pm.ActivityInfo
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.study2.xylophone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

//    soundPool 객체 초기화 setmaxStream(8) 음원파일을 한꺼번에 재생하는 음원갯수 지정
    private val soundPool = SoundPool.Builder().setMaxStreams(8).build()

//    listOf() 함수를 사용하여 텍스트 뷰와 음원파일의 리소스 id를 연관지은 Pair객체 8개를
//    리스트 객체 sounds로 만듭니다 .Pair클래스는 두개의 연관된 객체를 저장합니다
    private  val sounds by lazy {
        listOf(
            Pair(binding.do1, R.raw.do1),
            Pair(binding.re, R.raw.re),
            Pair(binding.mi, R.raw.mi),
            Pair(binding.fa, R.raw.fa),
            Pair(binding.sol, R.raw.sol),
            Pair(binding.la, R.raw.la),
            Pair(binding.si, R.raw.si),
            Pair(binding.do2, R.raw.do2)
        )
}
    override fun onCreate(savedInstanceState: Bundle?) {
//        가로모드 고정
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        sounds 리스트를 forEach() 함수를 사용하여 요소를 하나씩 꺼내서 tune() 메서드에 전달합니다.
        sounds.forEach{ tune(it)}
    }

    private fun tune(pitch: Pair<TextView, Int>) {  // Pair 객체를 받아서
        val soundId = soundPool.load(this,pitch.second,1) //load() 메서드로 음원의 ID를 얻고
//        전달받은 Pair 객체의 첫번째 프로퍼티인 텍스트 뷰를 얻고
        pitch.first.setOnClickListener {
//            텍스트 뷰를 클릭했을때 음원을 재생 합니다.
            soundPool.play(soundId,1.0f,1.0f,0,0,1.0f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        맵을 종료할때는 반드시 release() 메서드를 호출하여 SoundPool 객체의 자원을 해제합니다.
        soundPool.release()
    }
}