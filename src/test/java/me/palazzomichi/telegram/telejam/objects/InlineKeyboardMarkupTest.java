package me.palazzomichi.telegram.telejam.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InlineKeyboardMarkupTest {
  
  @Test
  public void fromColumns() {
    InlineKeyboardButton button1 = new CallbackDataInlineKeyboardButton("hello", "sayhello");
    InlineKeyboardButton button2 = new SwitchInlineQueryCurrentChatInlineKeyboardButton("test", "");
    InlineKeyboardMarkup keyboard1 = InlineKeyboardMarkup.fromColumns(
        3, button1, button1, button2, button1, button2
    );
    InlineKeyboardMarkup keyboard2 = new InlineKeyboardMarkup(
        new InlineKeyboardButton[][] {
            {button1, button1, button2},
            {button1, button2}
        }
    );
    assertEquals(keyboard1, keyboard2);
  }
  
}
