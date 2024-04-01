import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import CalculatorView from "../views/CalculatorView.vue";
import CheckListView from "../views/CheckListView.vue";
import RegionView from "../views/RegionView.vue";
import SurveyView from "../views/SurveyView.vue";
import MainComponent from "../components/main/MainComponent.vue";
import CalculatorComponent from "@/components/calculator/CalculatorComponent.vue";
import CalculatorResultComponent from "../components/calculator/CalculatorResultComponent.vue";
import CheckListComponent from "@/components/checklist/CheckListComponent.vue";
import CheckListResultComponent from "../components/checklist/CheckListResultComponent.vue";
import SurveyComponent from "@/components/survey/SurveyComponent.vue";
import SurveyResultComponent from "@/components/survey/SurveyResultComponent.vue";
// import MainComponent from "@/components/main/MainComponent.vue";
import MainComponentCC from "@/components/main/MainComponentCC.vue";
import RegionDetailComponent from "@/components/region/RegionDetailComponent.vue";
import RegionFamousComponent from "@/components/region/RegionFamousComponent.vue";
import CheckListComponentC from "@/components/checklist/CheckListComponentC.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: MainView,
      children: [
        {
          path: "/",
          name: "mainComponent",
          component: MainComponent,
        },
      ],
    },
    {
      path: "/survey",
      name: "survey",
      component: SurveyView,
      children: [
        {
          path: "/survey",
          name: "surveyComponent",
          component: SurveyComponent,
        },
        {
          path: "/survey/result",
          name: "surveyResult",
          props: true,
          component: SurveyResultComponent,
        },
      ]
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
      children: [
        {
          path: "/region",
          name: "regionFamousComponent",
          props: true,
          component: RegionFamousComponent,
        },
        {
          path: "/region/:id",
          name: "regionDetail",
          props: true,
          component: RegionDetailComponent,
        }
      ]
    },
    {
      path: "/calculator",
      name: "calculator",
      component: CalculatorView,
      children: [
        {
          path: "/calculator",
          name: "calculatorComponent",
          props: true,
          component: CalculatorComponent,
        },
        {
          path: "/calculator/result",
          name: "calculatorResult",
          props: true,
          component: CalculatorResultComponent,
        },
      ]
    },
    {
      path: "/checklist",
      name: "checklist",
      component: CheckListView,
      children: [
        {
          path: "/checklist",
          name: "checklistComponent",
          component: CheckListComponent,
        },
        {
          path: "/checklist/result",
          name: "checklistResult",
          component: CheckListResultComponent,
        },
      ]
    },
  ],
});

export default router;
