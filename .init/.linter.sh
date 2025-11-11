#!/bin/bash
cd /home/kavia/workspace/code-generation/notes-manager-222175-222184/notes_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

