import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import CalculatorView from "../views/CalculatorView.vue";
import ChecklistView from "../views/ChecklistView.vue";
import RegionView from "../views/RegionView.vue";
import SurveyView from "../views/SurveyView.vue";
import MainComponents from "../components/main/MainComponents.vue";
import CalculatorComponents from "../components/calculator/CalculatorResultComponents.vue";
import ChecklistResultComponents from "../components/checklist/ChecklistResultComponents.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "mainview",
      component: MainView,
      children: [
        {
          path: "/",
          name: "main",
          component: MainComponents,
        },
      ],
    },
    {
      path: "/survey",
      name: "survey",
      component: SurveyView,
    },
    {
      path: "/region/:regionId",
      name: "region",
      props: true,
      component: RegionView,
    },
    {
      path: "/calculator",
      name: "calculator",
      component: CalculatorView,
    },
    {
      path: "/calculator/result",
      name: "calculatorresult",
      component: CalculatorComponents,
    },
    {
      path: "/checklist",
      name: "checklist",
      component: ChecklistView,
    },
    {
      path: "/checklist/result",
      name: "checklistresult",
      component: ChecklistResultComponents,
    },
  ],
});

export default router;
