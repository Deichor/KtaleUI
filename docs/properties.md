# Common Properties

## All Elements

Every element inherits these properties:

| Property | Type | Description |
|---|---|---|
| `visible` | `Boolean` | Show/hide the element |
| `hitTestVisible` | `Boolean` | Whether the element receives mouse events |
| `tooltipText` | `String` | Tooltip text on hover |
| `tooltipTextSpans` | `List<LabelSpan>` | Rich text tooltip |
| `textTooltipStyle` | `TextTooltipStyle` | Tooltip appearance |
| `textTooltipShowDelay` | `Float` | Delay before showing tooltip |
| `anchor` | `Anchor` | Position and size |
| `padding` | `Padding` | Inner spacing |
| `flexWeight` | `Int` | Flex layout weight |
| `contentWidth` | `Int` | Explicit content width |
| `contentHeight` | `Int` | Explicit content height |
| `autoScrollDown` | `Boolean` | Auto-scroll to bottom |
| `keepScrollPosition` | `Boolean` | Preserve scroll position |
| `mouseWheelScrollBehaviour` | `MouseWheelScrollBehaviourType` | Scroll behavior |
| `background` | `PatchStyle` / `String` | Background texture |
| `maskTexturePath` | `String` | Mask texture path |
| `outlineColor` | `String` | Outline color |
| `outlineSize` | `Float` | Outline thickness |
| `overscroll` | `Boolean` | Allow overscroll |

## Container Elements

Container elements (`Panel`, `Group`, `Button`, `DynamicPane`, etc.) additionally have:

| Property | Type | Description |
|---|---|---|
| `layoutMode` | `LayoutMode` | Child arrangement mode |
| `scrollbarStyle` | `ScrollbarStyle` | Scrollbar appearance |
