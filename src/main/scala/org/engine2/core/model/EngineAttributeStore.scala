package org.engine2.core.model

import org.json4s.JsonFormat

trait EngineAttributeStore extends Serializable {
  def read[T: JsonFormat](imageId: ImageId, attributeName: String): T

  def write[T: JsonFormat](layerId: ImageId, attributeName: String, value: T): Unit

}
