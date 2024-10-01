import axios, { AxiosResponse } from "axios";
import { Card } from "../types/Card";

const BASE_PATH = "http://localhost:3300";

export const fetchCardsToReview = async (): Promise<AxiosResponse<Card[]>> => {
  const response = await axios(`${BASE_PATH}/cards-to-review`, {
    method: "GET",
  });

  return response;
};

export const saveNewCard = async (frontText: string, backText: string) => {
  var card = { front: frontText, back: backText };

  await axios(`${BASE_PATH}/cards`, {
    method: "POST",
    data: JSON.stringify(card),
  });
};

export const reviewCard = async (id: number, optionChoose: number) => {
  await axios(`${BASE_PATH}/review-card/id/${id}/next-review/${optionChoose}`, {
    method: "POST",
  });
}