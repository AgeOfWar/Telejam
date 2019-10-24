package io.github.ageofwar.telejam.examples.limitatibot

import io.github.ageofwar.telejam.Bot
import io.github.ageofwar.telejam.LongPollingBot
import io.github.ageofwar.telejam.examples.limitatibot.callback.Menu
import io.github.ageofwar.telejam.examples.limitatibot.commands.Start
import io.github.ageofwar.telejam.examples.limitatibot.messages.AdminAnswer
import io.github.ageofwar.telejam.examples.limitatibot.utils.InputListener
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Pass the bot token as unique program argument.")
        exitProcess(1)
    }

    val token = args[0]
    val bot = Bot.fromToken(token)

    SuperMarcomenBot(bot).run()

}

class SuperMarcomenBot(bot: Bot) : LongPollingBot(bot) {

    init {

        events.registerUpdateHandler(InputListener())
        events.registerUpdateHandler(Menu(bot))
        events.registerUpdateHandler(AdminAnswer(bot))
        events.registerCommand(Start(bot), "start")

    }

}