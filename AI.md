Gemini has been used to rewrite Parser class.

Changes:
1. 4 final fields extracted to specify datetime formats.
2. Conditional in parse converted to use enhanced switch statement.
3. Methods performing create, update, and delete have added conditional to check if input is empty.
4. Hardcoded substring indices replaced with split.