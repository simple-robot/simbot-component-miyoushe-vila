/*
 * Copyright (c) 2023. ForteScarlet.
 *
 * This file is part of simbot-component-miyoushe.
 *
 * simbot-component-miyoushe is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * simbot-component-miyoushe is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with simbot-component-miyoushe,
 * If not, see <https://www.gnu.org/licenses/>.
 */

package love.forte.simbot.component.miyoushe.internal.event

import love.forte.simbot.DiscreetSimbotApi
import love.forte.simbot.component.miyoushe.bot.VillaBot
import love.forte.simbot.component.miyoushe.event.VillaUnsupportedYetEvent
import love.forte.simbot.miyoushe.event.Event
import love.forte.simbot.miyoushe.event.EventExtendData
import love.forte.simbot.miyoushe.event.EventSource


/**
 *
 * @author ForteScarlet
 */
@OptIn(DiscreetSimbotApi::class)
internal class VillaUnsupportedYetEventImpl(
    override val bot: VillaBot,
    override val sourceEvent: Event<EventExtendData>,
    override val sourceEventSource: EventSource,
) : VillaUnsupportedYetEvent() {
}
