import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import CalculatorView from "../views/CalculatorView.vue";
import CheckListView from "../views/CheckListView.vue"
import RegionView from "../views/RegionView.vue";
import SurveyView from "../views/SurveyView.vue";
import MainComponent from "../components/main/MainComponent.vue";
import CalculatorComponent from "../components/calculator/CalculatorResultComponent.vue";
import CheckListResultComponent from "../components/checklist/CheckListResultComponent.vue";

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
          component: MainComponent,
        },
      ],
    },
    {
      path: "/survey",
      name: "survey",
      component: SurveyView,
    },
    // {
    //   path: "/region/:regionId",
    //   name: "region",
    //   props: true,
    //   component: RegionView,
    // },
    {
      path: "/region",
      name: "region",
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
      component: CalculatorComponent,
    },
    {
      path: "/checklist",
      name: "checklist",
      component: CheckListView,
    },
    {
      path: "/checklist/result",
      name: "checklistresult",
      component: CheckListResultComponent,
    },
  ],
});

export default router;
