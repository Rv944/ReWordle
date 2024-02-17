/**# Android Project 1 - *Wordle*

Submitted by: **Rothmel Fortune II**

**Wordle** is an android app that recreates a simple version of the popular word game [Wordle](https://www.nytimes.com/games/wordle/index.html).

Time spent: **X** hours spent in total

## Required Features

The following **required** functionality is completed:

- [ ] **User has 3 chances to guess a random 4 letter word**
- [ ] **After 3 guesses, user should no longer be able to submit another guess**
- [ ] **After each guess, user sees the "correctness" of the guess**
- [ ] **After all guesses are taken, user can see the target word displayed**

The following **optional** features are implemented:

- [ ] User can toggle betweeen different word lists
- [ ] User can see the 'correctness' of their guess through colors on the word
- [ ] User sees a visual change after guessing the correct word
- [ ] User can tap a 'Reset' button to get a new word and clear previous guesses
- [ ] User will get an error message if they input an invalid guess
- [ ] User can see a 'streak' record of how many words they've guessed correctly.

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with ...
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

## Notes

Describe any challenges encountered while building the app.

## License

Copyright [name of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.**/
/**# Android Project 1 - *Wordle*

Submitted by: **Rothmel Fortune II**

**Wordle** is an android app that recreates a simple version of the popular word game [Wordle](https://www.nytimes.com/games/wordle/index.html).

Time spent: **26** hours spent in total

## Required Features

The following **required** functionality is completed:

- [/] **User has 3 chances to guess a random 4 letter word**
- [/] **After 3 guesses, user should no longer be able to submit another guess**
- [/] **After each guess, user sees the "correctness" of the guess**
- [/] **After all guesses are taken, user can see the target word displayed**

The following **optional** features are implemented:

- [ ] User can toggle betweeen different word lists
- [ ] User can see the 'correctness' of their guess through colors on the word
- [/] User sees a visual change after guessing the correct word
- [/] User can tap a 'Reset' button to get a new word and clear previous guesses
- [ ] User will get an error message if they input an invalid guess
- [ ] User can see a 'streak' record of how many words they've guessed correctly.

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/link/to/your/gif/file.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<https://media.giphy.com/media/JhBUPwIQXrnfcbUVFm/giphy.gif>

## Notes

Describe any challenges encountered while building the app.

## License

Copyright [2024] [name of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**/

package com.example.wordle

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var guessCounter = 0
    private var correctnessStr = ""

    // Call helper class FourLetterWordList.kt to generate random 4 letter word
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    private var guessWord = ""
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessBtn = findViewById<Button>(R.id.guessBtn)
        val resetBtn = findViewById<Button>(R.id.resetBtn)

        val wordReveal = findViewById<TextView>(R.id.correctWord)

        val userInputField = findViewById<EditText>(R.id.userInputField)
        val firstWord = findViewById<TextView>(R.id.firstGuess)
        val guess1text = findViewById<TextView>(R.id.guess1text)
        val guess1checkText = findViewById<TextView>(R.id.guess1check)
        val correctness1 = findViewById<TextView>(R.id.correctness1)

        val secondWord = findViewById<TextView>(R.id.secondGuess)
        val guess2text = findViewById<TextView>(R.id.guess2text)
        val guess2checkText = findViewById<TextView>(R.id.guess2check)
        val correctness2 = findViewById<TextView>(R.id.correctness2)

        val thirdWord = findViewById<TextView>(R.id.thirdGuess)
        val guess3text = findViewById<TextView>(R.id.guess3text)
        val guess3checkText = findViewById<TextView>(R.id.guess3check)
        val correctness3 = findViewById<TextView>(R.id.correctness3)


        // Used to Display random word to screen
        wordReveal.text = wordToGuess

        guessBtn.setOnClickListener {
            if (guessCounter == 0) {
                // Save user input, clear text field and close keyboard
                saveInputAndCloseKeyboard(firstWord, userInputField)

                // Show user's first guess
                displayUsersGuess(firstWord, guess1text)

                // Check user's first word for correctness
                checkAndDisplayCorrectness(guess1checkText, correctness1)

                // If user gets the word right on the first try, reveal reset button and word
                if (firstWord.text == wordToGuess) {
                    guessBtn.isEnabled = false
                    guessBtn.isClickable = false
                    wordReveal!!.visibility = View.VISIBLE
                    resetBtn.visibility = View.VISIBLE
                }
            }
            else if (guessCounter == 1) {
                // Save user input, display on screen, and clear text field
                saveInputAndCloseKeyboard(secondWord, userInputField)

                // Show user's second guess
                displayUsersGuess(secondWord, guess2text)

                // Check user's second word for correctness
                checkAndDisplayCorrectness(guess2checkText, correctness2)

                if (secondWord.text == wordToGuess) {
                    guessBtn.isEnabled = false
                    guessBtn.isClickable = false
                    wordReveal!!.visibility = View.VISIBLE
                    resetBtn.visibility = View.VISIBLE
                }

            }
            else {
                // Save user input, clear text field and close keyboard
                saveInputAndCloseKeyboard(thirdWord, userInputField)

                // Show user's third guess
                displayUsersGuess(thirdWord, guess3text)

                // Check user's third word for correctness
                checkAndDisplayCorrectness(guess3checkText, correctness3)

                if (thirdWord.text == wordToGuess) {
                    guessBtn.isEnabled = false
                    guessBtn.isClickable = false
                    wordReveal!!.visibility = View.VISIBLE
                    resetBtn.visibility = View.VISIBLE
                }
            }

            // Once user reaches guess limit OR guesses answer right, disable button and reveal answer
            if (guessCounter == 3) {
                guessBtn.isEnabled = false
                guessBtn.isClickable = false
                wordReveal.visibility = View.VISIBLE
                resetBtn.visibility = View.VISIBLE
            }
        }
        resetBtn.setOnClickListener {
            // reset counter
            guessCounter = 0

            // Enable buttons and hide word and reset button
            guessBtn.isEnabled = true
            guessBtn.isClickable = true
            wordReveal.visibility = View.INVISIBLE
            resetBtn.visibility = View.INVISIBLE

            // Clear the TextViews for each word
            firstWord.text = ""
            secondWord.text = ""
            thirdWord.text = ""

            // Set TextViews to invisible
            firstWord.visibility = View.INVISIBLE
            secondWord.visibility = View.INVISIBLE
            thirdWord.visibility = View.INVISIBLE

            guess1checkText.visibility = View.INVISIBLE
            guess2checkText.visibility = View.INVISIBLE
            guess3checkText.visibility = View.INVISIBLE

            guess1text.visibility = View.INVISIBLE
            guess2text.visibility = View.INVISIBLE
            guess3text.visibility = View.INVISIBLE

            correctness1.visibility = View.INVISIBLE
            correctness2.visibility = View.INVISIBLE
            correctness3.visibility = View.INVISIBLE
        }
    }

    private fun checkAndDisplayCorrectness(textToDisplay: TextView?, correctness: TextView?) {
        correctnessStr = checkGuess(guessWord, wordToGuess)
        guessCounter++
        if (correctness != null) {
            correctness.text = correctnessStr
            correctness.visibility = View.VISIBLE
        }
        if (textToDisplay != null) {
            textToDisplay.visibility = View.VISIBLE
        }
    }

    private fun displayUsersGuess(wordToDisplay: TextView?, title: TextView?) {
        if (wordToDisplay != null) {
            wordToDisplay.visibility = View.VISIBLE
        }
        if (title != null) {
            title.visibility = View.VISIBLE
        }
    }


    private fun saveInputAndCloseKeyboard(guessInput: TextView, userInputField: EditText) {
        guessWord = userInputField.text.toString().uppercase()
        guessInput.text = guessWord
        userInputField.text?.clear()
        closeKeyboard()
        userInputField.requestFocus()
    }


    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String, wordToGuess: String) : String {
        var result = ""
        for (i in 0..3) {
            result += if (guess[i] == wordToGuess[i]) {
                "O"
            } else if (guess[i] in wordToGuess) {
                "+"
            } else {
                "X"
            }
        }
        return result
    }

    // extension function to hide soft keyboard programmatically
    private fun Activity.closeKeyboard(){
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

}
