package org.engine2.tiling

import org.scalatest._

import geotrellis.vector.Extent
import geotrellis.raster.CellSize
import geotrellis.spark.tiling._

class ZoomableFloatingLayoutSchemeSpec extends FlatSpec with Matchers {

  val scheme: LayoutScheme = ZoomableFloatingLayoutScheme(10, 0.5)

  val level: LayoutLevel = scheme.levelFor(Extent(0, 0, 37, 27), CellSize(1, 1))

  "ZoomableFloatingLayoutScheme" should "should pad the layout to match source resolution" in {
    level.layout.tileLayout.totalCols should be(40)
    level.layout.tileLayout.totalRows should be(30)
  }

  it should "pop values in last-in-first-out order" in {
    level.layout.extent should be(Extent(0, -3, 40, 27))
  }

  it should "have enough tiles to cover the source extent" in {
    level.layout.layoutCols should be(4)
    level.layout.layoutRows should be(3)
  }

}
