$(document).ready(function () {

    $('.icon-info-svg').html('<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 24 24">\n' +
        '<path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />\n' +
        '<path d="M8.93 6.588l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z" />\n' +
        '</svg>');

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    let activityLevelMap = new Map();
    activityLevelMap["MILDLY"] = 1.2;
    activityLevelMap["LIGHT"] = 1.4;
    activityLevelMap["MEDIUM"] = 1.6;
    activityLevelMap["INTENSE"] = 2.0;

    let sportGoalMap = new Map();
    sportGoalMap["FAT_LOSS"] = 0.85;
    sportGoalMap["HOLD_MUSCLE"] = 1;
    sportGoalMap["GAIN_MUSCLE"] = 1.1;

    let somatotypeMap = new Map();
    somatotypeMap["ECTOMORPH"] = 1.1;
    somatotypeMap["ECTO_MEZOMORPH"] = 1.05;
    somatotypeMap["MESOMORPH"] = 1.0;
    somatotypeMap["ENDO_MESOMORPH"] = 0.95;
    somatotypeMap["ENDOMORPH"] = 0.9;

    const proteinIncomes = {
        "MAN": {
            "FAT_LOSS": 2.5,
            "HOLD_MUSCLE" : 1.5,
            "GAIN_MUSCLE" : 2.0,
        },
        "WOMAN": {
            "FAT_LOSS": 1.5,
            "HOLD_MUSCLE" : 0.5,
            "GAIN_MUSCLE" : 1.0,
        }
    };

    function calcAndShowMacros() {
        const weight = parseFloat($('#inputWeight').val());
        const fat = parseFloat($('#inputFat').val());
        const age = parseFloat($('#inputAge').val());
        const height = parseFloat($('#inputHeight').val());
        const sex = $('#inputSex').val();
        const activityLevel = $('#inputActivity').val();
        const somatotype = $('#inputSomatotype').val();
        const sportGoal = $('#inputSportGoal').val();
        const maxProtein = parseFloat($('#inputMaxProtein').val());
        const maxCarbohydrates = parseFloat($('#inputMaxCarbs').val());

        const weightUnit = $('#btnWeight').html();
        const heightUnit = $('#btnHeight').html();
        const maxProteinUnit = $('#btnMaxProtein').html();
        const maxCarbsUnit = $('#btnMaxCarbs').html();

        // convert to standard units
        const weightVal = weightUnit === 'lb' ? weight * 0.45359237 : weight;
        const heightVal = heightUnit === 'in' ? height * 2.54 : height;
        const maxProteinVal = maxProteinUnit === 'oz' ? maxProtein * 28.3495231 : maxProtein;
        const maxCarbsVal = maxCarbsUnit === 'oz' ? maxCarbohydrates * 28.3495231 : maxCarbohydrates;

        if (weightVal > 0 && fat > 0 && age > 0 && heightVal > 0) {
            const lbm = weightVal * (100 - fat) / 100;
            const bmr = sex === 'MAN' ? 66 + (13.7 * lbm) + (5 * heightVal) - (6.8 * age)
                : 655 + (9.6 * lbm) + (1.8 * heightVal) - (4.7 * age);
            const tdee = bmr * activityLevelMap[activityLevel];
            const tdee2 = tdee * somatotypeMap[somatotype];
            const tdee3 = tdee2 * sportGoalMap[sportGoal];

            let proteinIncome = lbm * proteinIncomes[sex][sportGoal];
            if (maxProteinVal > 0 && proteinIncome > maxProteinVal) {
                proteinIncome = maxProteinVal;
            }

            let carbsIncome = ((tdee3 - (proteinIncome * 4)) * 0.65) / 4;
            if (maxCarbsVal > 0 && carbsIncome > maxCarbsVal) {
                carbsIncome = maxCarbsVal;
            }

            const fatIncome = (tdee3 - (carbsIncome * 4) - (proteinIncome * 4)) / 9;

            const bmi = weightVal / ((heightVal/100) * (heightVal/100));

            $('#tdLbm').html(`${lbm.toFixed(2)} kg / ${(lbm * 2.20462262).toFixed(2)} lb`);
            $('#tdBmr').html(`${bmr.toFixed()} kCal`);
            $('#tdTdee').html(`${tdee.toFixed()} kCal`);
            $('#tdTdee2').html(`${tdee2.toFixed()} kCal`);
            $('#tdTdee3').html(`${tdee3.toFixed()} kCal`);

            $('#tdProteinIncome').html(`${proteinIncome.toFixed()} g / ${(proteinIncome * 0.0352739619).toFixed(4)} oz`);
            $('#tdCarbsIncome').html(`${carbsIncome.toFixed()} g / ${(carbsIncome * 0.0352739619).toFixed(4)} oz`);
            $('#tdFatIncome').html(`${fatIncome.toFixed()} g / ${(fatIncome * 0.0352739619).toFixed(4)} oz`);
            $('#tdBMI').html(`${bmi.toFixed(2)}`);
        }
    }

    function validateAndCalc() {
        const form = $('#formUserData');
        form.validate({
            errorPlacement: function(label, element) {
                label.addClass('invalid-feedback');
                label.insertAfter(element);
            },
            wrapper: 'div'
        });
        if(form.valid()) {
            calcAndShowMacros();
        }
    }

    $('#btnSubmit').on('click', validateAndCalc);
    $('.recalcInput').change(validateAndCalc);

    new jBox('Tooltip', {
        attach: '.infoTooltip'
    });

    $('#hrefSomatotypeInfo').on('click', function () {
        $('#modalSomatotype').modal('show');
    });

    $('#btnCloseSomatotypeModal').on('click', function () {
        $('#modalSomatotype').modal('hide');
    });

    $('#hrefBMIInfo').on('click', function () {
        $('#modalBMI').modal('show');
    });

    $('#btnCloseBMIModal').on('click', function () {
        $('#modalBMI').modal('hide');
    });

    $(".dropdown-menu li a").click(function() {
        $(this).parents(".dropdown").find('.btn').html($(this).text());
        $(this).parents(".dropdown").find('.btn').val($(this).data('value'));

        // change min / max
        const input = $(this).parents(".dropdown").find('.recalcInput');
        const inputId = input.attr('id');

        if (inputId === 'inputWeight') {
            const btnVal = $('#btnWeight').text();
            input.attr({
                "min" : btnVal === 'lb' ? "22" : "10",
                "max" : btnVal === 'lb' ? "441" : "200",
            });
        } else if (inputId === 'inputHeight') {
            const btnVal = $('#btnHeight').text();
            input.attr({
                "min" : btnVal === 'in' ? "20" : "50",
                "max" : btnVal  === 'in' ? "99" : "250",
            });
        } else if (inputId === 'inputMaxProtein') {
            const btnVal = $('#btnMaxProtein').text();
            input.attr({
                "max" : btnVal  === 'oz' ? "36" : "1",
            });
        } else if (inputId === 'inputMaxCarbs') {
            const btnVal = $('#btnMaxCarbs').text();
            input.attr({
                "max" : btnVal  === 'oz' ? "36" : "1",
            });
        }

        validateAndCalc();
    });

});