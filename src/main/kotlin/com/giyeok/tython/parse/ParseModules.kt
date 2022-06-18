package com.giyeok.tython.parse

import java.io.File

class ParseModules(
  val pwd: File,
  val dest: File,
  val pythonPath: String,
  val conversionMainPy: String
) {
  companion object {
    val INSTNACE = ParseModules(
      File("/home/joonsoo/Documents/workspace/tython/src/main/python"),
      File("/home/joonsoo/Documents/workspace/tython/src/main/python/pb"),
      "/home/joonsoo/miniconda3/envs/py310/bin/python",
      "conversions_main.py",
    )
  }

  fun load(srcs: Map<String, String>): Map<String, Module> {
    val processBuilder = ProcessBuilder(
      pythonPath,
      conversionMainPy,
      dest.absolutePath,
      *(srcs.flatMap { (moduleName, srcName) ->
        listOf(File(pwd, srcName).absolutePath, moduleName)
      }.toTypedArray())
    ).directory(pwd).inheritIO()
    val process = processBuilder.start()
    process.waitFor()

    return srcs.keys.associateWith { moduleName ->
      File(dest, "$moduleName.pb").inputStream().use {
        Module.fromProto(com.giyeok.tython.proto.Module.parseFrom(it))
      }
    }
  }
}
