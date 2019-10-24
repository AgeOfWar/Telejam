package io.github.ageofwar.telejam.examples.limitatibot.callback

import io.github.ageofwar.telejam.Bot
import io.github.ageofwar.telejam.callbacks.CallbackDataHandler
import io.github.ageofwar.telejam.callbacks.CallbackQuery
import io.github.ageofwar.telejam.inline.CallbackDataInlineKeyboardButton
import io.github.ageofwar.telejam.inline.InlineKeyboardButton
import io.github.ageofwar.telejam.messages.Message
import io.github.ageofwar.telejam.methods.AnswerCallbackQuery
import io.github.ageofwar.telejam.methods.EditMessageText
import io.github.ageofwar.telejam.methods.ForwardMessage
import io.github.ageofwar.telejam.methods.SendMessage
import io.github.ageofwar.telejam.replymarkups.InlineKeyboardMarkup
import io.github.ageofwar.telejam.text.Text
import io.github.ageofwar.telejam.users.User
import io.github.ageofwar.telejam.examples.limitatibot.SUPPORTED_LANGUAGES
import io.github.ageofwar.telejam.examples.limitatibot.utils.AskMessage
import io.github.ageofwar.telejam.examples.limitatibot.utils.InputListener
import java.io.IOException
import java.util.*

class Menu(private val bot: Bot) : CallbackDataHandler, AskMessage {

    override fun onInputReceived(message: Message) {
        val user = message.sender
        InputListener.removeUser(user)

        val buttons = arrayOf<InlineKeyboardButton>(CallbackDataInlineKeyboardButton("Back", "menu_back"))

        val sendMessage = SendMessage()
                .chat(message.chat)
                .replyMarkup(InlineKeyboardMarkup(buttons))
                .text("✅ Your message was successfully sent")

        bot.execute(sendMessage)

        val forwardMessage = ForwardMessage()
                .message(message)
                .chat(229856560L)

        val messageId = bot.execute(forwardMessage)

        val replyMessage = SendMessage()
                .replyToMessage(messageId)
                .text(Text.parseHtml(getMessage(message.sender)))
                .chat(229856560L)

        bot.execute(replyMessage)
    }

    private fun getMessage(user: User): String {
        return "<b>Name:</b> <a href=\"tg://user?id=%user_id\">%first_name %last_name</a>\n<b>Username:</b> %username"
                .replace("%first_name", user.firstName)
                .replace("%last_name", user.lastName.orElse(""))
                .replace("%username", user.username.orElse(""))
                .replace("%user_id", user.id.toString())
    }

    @Throws(IOException::class)
    override fun onCallbackData(callbackQuery: CallbackQuery, s: String, s1: String) {
        if (!callbackQuery.data.isPresent) return
        val user = callbackQuery.sender

        when {
            callbackQuery.data.get() == "contact_me" -> {
                InputListener.listenFor(user, this)

                val buttons = arrayOf<InlineKeyboardButton>(CallbackDataInlineKeyboardButton("🔙 Back", "menu_back"))

                val editMessageText = EditMessageText()
                        .callbackQuery(callbackQuery)
                        .replyMarkup(InlineKeyboardMarkup(buttons))
                        .text(Text.parseHtml("📝 Now you can write me your message and/or send a file"))

                bot.execute(editMessageText)
            }
        }

        if (callbackQuery.data.get() == "menu_back" || callbackQuery.data.get().startsWith("choosen_language")) {

            val buttons = arrayOf<InlineKeyboardButton>(CallbackDataInlineKeyboardButton("🌍 Change language", "choose_language"), CallbackDataInlineKeyboardButton("👤 Contact me", "contact_me"))

            val editMessageText = EditMessageText()
                    .callbackQuery(callbackQuery)
                    .replyMarkup(InlineKeyboardMarkup(buttons))
                    .text(Text.parseHtml("🏻 Hi, this is an example of a bot like @LimitatiBot."))

            bot.execute(editMessageText)
        }

    }
}