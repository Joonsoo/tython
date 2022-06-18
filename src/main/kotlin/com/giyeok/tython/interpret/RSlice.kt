package com.giyeok.tython.interpret

class RSlice {
}

fun rasterSlice(slice: ClassInstanceValue, totalSize: Int): RSlice {
  val step = (slice.attrs["step"] as? IntValue)?.value?.toInt() ?: 1

  val start = (slice.attrs["start"] as? IntValue)?.value?.toInt() ?: 0
  val stop0 = (slice.attrs["stop"] as? IntValue)?.value?.toInt()
  val stop = when {
    stop0 == null -> totalSize
    stop0 < 0 -> totalSize + stop0
    else -> stop0
  }
  println("$start $stop $step")
  return RSlice()
}
