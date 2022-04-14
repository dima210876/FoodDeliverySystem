import React from "react"

import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import {useSelector} from "react-redux";

function LineChart() {

    const statisticsData = useSelector(state => state.statistics.statisticsData);
    function dateMonthAgo() {
        let today = new Date();
        return today.setTime(today.getTime() - 28*24*60*60);
    }
    const optionsMoney = {
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Income statistics for the last month'
        },
        yAxis: {
            title: {
                text: 'Money earned'
            }
        },
        xAxis: {
            title: {
                text: 'Date'
            },
            type: 'datetime'
        },
        series: [{
            name: "Number of orders",
            data: statisticsData.ordersNumberPerDay,
            pointStart: dateMonthAgo(),
            pointInterval: 3600 * 1000 * 24
        }]
    };
        const optionsOrdersNumber = {
            chart: {
                type: 'spline'
            },
            title: {
                text: 'Orders statistics for the last month'
            },
            yAxis: {
                title: {
                    text: 'Number of orders'
                }
            },
            xAxis: {
                title: {
                    text: 'Date'
                },
                type: 'datetime'
            },
            colors: ['#B22222'],
            series: [{
                name: "Number of orders",
                data: statisticsData.incomePerDay,
                pointStart: dateMonthAgo(),
                pointInterval: 3600 * 1000 * 24
            }]
    };
    return (
        <div>
            <div className="chart">
                <HighchartsReact highcharts={Highcharts} options={optionsMoney} />
            </div>
            <div className="chart">
                <HighchartsReact highcharts={Highcharts} options={optionsOrdersNumber} />
            </div>
        </div>
    )
}

export default LineChart