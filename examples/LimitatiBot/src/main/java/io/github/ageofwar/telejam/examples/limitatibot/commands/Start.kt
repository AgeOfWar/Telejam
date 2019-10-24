package io.github.ageofwar.telejam.examples.limitatibot.commands

import io.github.ageofwar.telejam.Bot
import io.github.ageofwar.telejam.commands.Command
import io.github.ageofwar.telejam.commands.CommandHandler
import io.github.ageofwar.telejam.inline.CallbackDataInlineKeyboardButton
import io.github.ageofwar.telejam.messages.TextMessage
import io.github.ageofwar.telejam.methods.SendMessage
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup
import io.github.ageofwar.telejam.text.Text

class Start(private val bot: Bot) : CommandHandler {

    override fun onCommand(command: Command, message: TextMessage) {
        val user = message.sender
        val buttons = arrayOf(
                CallbackDataInlineKeyboardButton("👤 Contact me", "contact_me")
        )

        val sendMessage = SendMessage()
                .chat(message.chat)
                .replyMarkup(InlineKeyboardMarkup(buttons))
                .text(Text.parseHtml("🏻 Hi, this is an example of a bot like @LimitatiBot."))

        bot.execute(sendMessage)
    }
}