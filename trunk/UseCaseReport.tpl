RHAP_REP_TEMPLATE
"Prints all use cases and use case diagrams in a model."
{
version=
{
template=
{
major=2,
minor=0
},
metameta=
{
major=1,
minor=6
},
metamodel=
{
kind="rhapsody",
major=2,
minor=3
}
},
properties=
{
name="",
named_values=[]
},
documents=
[
{
label="",
description="",
preferred_document_type="",
defaults=
{
no_model_data_text=
[
TEXT "<<No Model Data>>"
],
skip_preceding?=false,
skip_following?=false,
omit_if_no_iteration_data?=true,
alternate_text_only?=true,
alternate_title=
[
TEXT "<<No Model Data>>"
],
alternate_text=
[
TEXT "<<No Model Data>>"
],
named_values=[]
},
document_options=
[
{
type="Word",
options=
[
{
name="WordTemplateName",
value=STRING "usecasereport.dot"
}
]
},
{
type="Html",
options=
[
{
name="StyleSheet",
value=STRING "C:\\Program Files (x86)\\IBM\\Rational\\Rhapsody\\7.5.3\\reporterplus\\default.css"
}
]
}
],
named_values=[],
nodes=
[
TEXT 
{
label="Hyper-Drive Programming",
description="",
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
JUSTIFICATION RIGHT_ALIGNED,
FONT_SIZE
[
TEXT "18"
],
FONT BOLD? true,
TEXT "<Company Name>",
FONT BOLD? false,
FONT_SIZE
[
TEXT "10"
],
LINE_BREAK
],
COMPOSITE
[
LINE_BREAK
],
COMPOSITE
[
LINE_BREAK
]
],
nodes=
[

]
},
TEXT 
{
label="Doc name, header, footer, toc",
description="",
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
JUSTIFICATION CENTERED,
FONT_SIZE
[
TEXT "18"
],
FONT BOLD? true,
TEXT "Use Case Report for Model ",
FONT ITALIC? true,
ATTRIBUTE
{
expr="$name",
no_attribute_data=NONE
},
FONT ITALIC? false,
FONT BOLD? false,
LINE_BREAK
],
COMPOSITE
[
BEGIN_HEADER,
JUSTIFICATION CENTERED,
BEGIN_TABLE,
NEW_ROW
[

]
],
COMPOSITE
[
NEW_COLUMN
[

]
],
COMPOSITE
[
TEXT "Project Name: ",
ATTRIBUTE
{
expr="$name of model",
no_attribute_data=NONE
}
],
COMPOSITE
[
NEW_COLUMN
[

]
],
COMPOSITE
[
TEXT "Issue: <1.0>",
NEW_ROW
[

]
],
COMPOSITE
[
NEW_COLUMN
[

]
],
COMPOSITE
[
TEXT "Use Case Report"
],
COMPOSITE
[
NEW_COLUMN
[

]
],
COMPOSITE
[
TEXT "Issue Date: ",
FIELD DATE,
END_TABLE,
END_HEADER,
JUSTIFICATION LEFT_ALIGNED,
BEGIN_FOOTER,
JUSTIFICATION CENTERED,
TEXT "Confidential                                            (c)<Company Name>, 2003                                          Page ",
FIELD PAGE_NUMBER,
END_FOOTER,
PAGE_BREAK,
FONT_SIZE
[
TEXT "12"
],
TOC
[

],
FONT_SIZE
[
TEXT "10"
],
JUSTIFICATION LEFT_ALIGNED,
PAGE_BREAK
]
],
nodes=
[
ITERATION 
{
label="Use Cases in Model",
description="",
iteration=
{
source=CLASS "UseCase",
condition=SOME
ADVANCED "(* To restrict the report to a specific use case, insert the  name of the use case  between\r\n    the double quotes  in the line of code  following this comment,\r\n     e.g.. let usecase_name = \"My Use Case\" \r\n    If you want all the use cases, then delete all text between the double quotes. You may use\r\n    a regular expression for the use case name to select multiple use cases. *)\r\n\r\n(let usecase_name = \"\" in\r\n\r\n  if( usecase_name = \"\"  )\r\n         then true\r\n         else $name  ~= usecase_name\r\n)",
sort=
[
{
expr="$name",
ascending?=true,
numeric?=false,
case_sensitive?=false
}
],
recursive?=false,
separator=[],
no_iteration_data=TEXT_ONLY
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
TEXT "No use cases in the model.",
LINE_BREAK
]
],
table?=true,
create_refs?=true
},
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
],
COMPOSITE
[
TEXT "UseCases",
LINE_BREAK
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
]
],
nodes=
[
TEXT 
{
label="iterate over all use cases, sorted by name",
description="",
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
],
COMPOSITE
[
ATTRIBUTE
{
expr="$name",
no_attribute_data=NONE
},
LINE_BREAK
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
TEXT "Documentation: ",
ATTRIBUTE
{
expr="$descriptionHTML",
no_attribute_data=SOME
{
alternate_text=DEFAULT,
preceding_text=SKIP,
following_text=SKIP
}
},
LINE_BREAK
]
],
nodes=
[

]
}
]
},
ITERATION 
{
label="Use Case Diagrams in Model",
description="",
iteration=
{
source=CLASS "UsecaseDiagram",
condition=NONE,
sort=
[
{
expr="$name",
ascending?=true,
numeric?=false,
case_sensitive?=false
}
],
recursive?=false,
separator=[],
no_iteration_data=DEFAULT,
table?=false,
create_refs?=true
},
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
],
COMPOSITE
[
TEXT "UseCase Diagrams",
LINE_BREAK
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
LINE_BREAK
]
],
nodes=
[
TEXT 
{
label="iterate over use case diagrams, sorted by name",
description="",
heading=
[
COMPOSITE
[
STYLE "AutoHeading"
],
COMPOSITE
[
ATTRIBUTE
{
expr="$name",
no_attribute_data=NONE
},
LINE_BREAK
]
],
body=
[
COMPOSITE
[
STYLE "AutoNormal"
],
COMPOSITE
[
IMAGE_HYPERLINK
{
filename=
[
ATTRIBUTE "$path"
],
location=
[

]
},
LINE_BREAK
]
],
nodes=
[

]
}
]
}
]
}
]
}
]
}
