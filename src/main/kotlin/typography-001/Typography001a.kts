@file:Suppress("UNUSED_LAMBDA_EXPRESSION")

import org.openrndr.Program
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.FontImageMap
import org.openrndr.draw.RenderTarget
import org.openrndr.extensions.Screenshots
import org.openrndr.extra.compositor.compose
import org.openrndr.extra.compositor.draw
import org.openrndr.extra.compositor.layer
import org.openrndr.extra.compositor.post
import org.openrndr.filter.blur.GaussianBlur
import org.openrndr.math.Vector2
import org.openrndr.workshop.toolkit.filters.StepWaves
import org.openrndr.workshop.toolkit.filters.VerticalStepWaves
import org.openrndr.workshop.toolkit.filters.VerticalWaves


{ program: Program ->
    program.apply {


        extend(Screenshots().apply {
            scale = 4.0
        })

        val poster = compose {
            layer {
                post(GaussianBlur()) {
                    gain = 1.0
                    spread = 1.0
                    window = 5
                    sigma = Math.cos(seconds) * 5.0 + 5.0
                }
                draw {

                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 64.0, 2.0)
                    drawer.text("Tim Blurton", 20.0, 250.0)
                }
            }
            layer {
                draw {
                    drawer.fontMap = FontImageMap.fromUrl("file:data/fonts/IBMPlexMono-Regular.ttf", 32.0, 2.0)
                    drawer.text("My name is", 20.0, 200.0)
                }
            }
        }

        extend {
            poster.draw(drawer)
        }
    }
}