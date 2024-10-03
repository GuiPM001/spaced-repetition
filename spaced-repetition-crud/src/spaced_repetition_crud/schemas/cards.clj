(ns spaced-repetition-crud.schemas.cards
  (:require [schema.core :as s]))

(s/defschema NewCardDTO
  {:front s/Str
   :back s/Str})

(s/defschema CardDTO
  {:id s/Int
   :front s/Str
   :back s/Str
   :goodOption s/Int
   :badOption s/Int})
