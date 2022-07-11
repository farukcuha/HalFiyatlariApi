#!/usr/bin/env bash
cd /Users/ahmetfarukcuha/Desktop/projects/HalFiyatlariApi
rm hal_fiyatlari_database.mv.db
rm hal_fiyatlari_database.trace.db
heroku ps:copy hal_fiyatlari_database.mv.db --app halfiyatlariapi
